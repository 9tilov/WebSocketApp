package com.d9tilov.android.websockettestapp.settings.data

import com.d9tilov.android.websockettestapp.base.data.db.AppDatabase
import com.d9tilov.android.websockettestapp.base.exception.RxWebSocketException
import com.d9tilov.android.websockettestapp.base.socket.RxWebSocket
import com.d9tilov.android.websockettestapp.settings.data.local.entity.ExchangePairDbModel
import com.d9tilov.android.websockettestapp.settings.data.local.mapper.SettingsMapper
import com.d9tilov.android.websockettestapp.settings.domain.SettingsRepo
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

class ExchangeSettingsRepo(
    private val appDatabase: AppDatabase,
    private val mapper: SettingsMapper,
    private val rxWebSocket: RxWebSocket
) : SettingsRepo {

    private val settingsDao = appDatabase.settingsDao()

    override fun updateExchangeRateSelection(exchangePairDataModel: ExchangePairDataModel): Completable {
        val result = if (exchangePairDataModel.isActive) {
            rxWebSocket.subscribe("${exchangePairDataModel.from.name}${exchangePairDataModel.to.name}")
        } else {
            rxWebSocket.unsubscribe("${exchangePairDataModel.from.name}${exchangePairDataModel.to.name}")
        }
        return if (result) {
            settingsDao.update(mapper.from(exchangePairDataModel))
        } else {
            Completable.error(RxWebSocketException("Can't unsubscribe from ${exchangePairDataModel.from}${exchangePairDataModel.to}"))
        }
    }

    override fun getAllExchangeRatePairs(): Flowable<List<ExchangePairDataModel>> {
        return appDatabase.settingsDao().getCount().flatMapPublisher {
            settingsDao.getList().flatMapSingle { list: List<ExchangePairDbModel> ->
                Flowable.fromIterable(
                    list.sortedWith(
                        compareBy(
                            { it.from },
                            { it.to })
                    )
                ).map { item -> mapper.to(item) }.toList()
            }
        }
    }

    override fun getActiveExchangeRatePairs(): Flowable<List<ExchangePairDataModel>> {
        return settingsDao.getActiveList().firstElement()
            .flatMapSingle {
                Observable.fromIterable(it).map { item -> mapper.to(item) }.toList()
            }.toFlowable()
    }
}

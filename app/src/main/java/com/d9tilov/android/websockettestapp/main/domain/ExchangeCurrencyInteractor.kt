package com.d9tilov.android.websockettestapp.main.domain

import com.d9tilov.android.websockettestapp.main.ui.entity.DataItem
import com.d9tilov.android.websockettestapp.main.ui.mapper.RatesUiMapper
import com.d9tilov.android.websockettestapp.settings.domain.SettingsInteractor
import io.reactivex.Completable
import io.reactivex.Observable

class ExchangeCurrencyInteractor(
    private val exchangeRepo: ExchangeRepo,
    private val settingsInteractor: SettingsInteractor,
    private val mapper: RatesUiMapper
) : ExchangeInteractor {

    override fun startFetchData(): Completable =
        settingsInteractor.getActiveExchangeRatePairs()
            .flatMapCompletable { exchangeRepo.startFetchData(it) }

    override fun stopFetchData(): Completable = exchangeRepo.stopFetchData()

    override fun getExchangeRate(): Observable<List<DataItem.ExchangeRateUiItem>> {
        return exchangeRepo.getExchangeRate().map { list -> list.map { mapper.to(it) } }
    }
}
package com.d9tilov.android.websockettestapp.prepopulate.data

import com.d9tilov.android.websockettestapp.base.data.db.AppDatabase
import com.d9tilov.android.websockettestapp.prepopulate.domain.PrepopulateRepo
import com.d9tilov.android.websockettestapp.settings.data.local.mapper.SettingsMapper
import io.reactivex.Completable
import io.reactivex.Observable

class PrepopulateDataRepo(
    private val defaultExchangePairCreator: DefaultExchangePairCreator,
    private val appDatabase: AppDatabase,
    private val mapper: SettingsMapper,
) : PrepopulateRepo {

    private val settingsDao = appDatabase.settingsDao()

    override fun prepopulateItemsIfNeed(): Completable {
        return appDatabase.settingsDao().getCount().flatMapCompletable {
            if (it == 0) {
                val pairs = defaultExchangePairCreator.createDefaultExchangePairs()
                Observable.fromIterable(pairs)
                    .flatMapCompletable { pair -> settingsDao.insert(mapper.from(pair)) }
            } else {
                Completable.complete()
            }
        }
    }
}
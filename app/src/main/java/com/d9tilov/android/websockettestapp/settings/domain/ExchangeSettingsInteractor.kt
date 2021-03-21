package com.d9tilov.android.websockettestapp.settings.domain

import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import io.reactivex.Completable
import io.reactivex.Flowable

class ExchangeSettingsInteractor(private val settingsRepo: SettingsRepo) : SettingsInteractor {

    override fun updateSelection(pair: ExchangePairDataModel): Completable =
        settingsRepo.updateExchangeRateSelection(pair)

    override fun getAllExchangeRatePairs(): Flowable<List<ExchangePairDataModel>> =
        settingsRepo.getAllExchangeRatePairs()

    override fun getActiveExchangeRatePairs(): Flowable<List<ExchangePairDataModel>> {
        return settingsRepo.getActiveExchangeRatePairs()
    }
}
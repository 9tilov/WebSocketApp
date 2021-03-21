package com.d9tilov.android.websockettestapp.settings.domain

import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface SettingsRepo {
    fun updateExchangeRateSelection(exchangePairDataModel: ExchangePairDataModel): Completable
    fun getAllExchangeRatePairs(): Flowable<List<ExchangePairDataModel>>
    fun getActiveExchangeRatePairs(): Flowable<List<ExchangePairDataModel>>
}

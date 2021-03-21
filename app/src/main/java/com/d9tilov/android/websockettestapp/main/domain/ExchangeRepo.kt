package com.d9tilov.android.websockettestapp.main.domain

import com.d9tilov.android.websockettestapp.main.domain.entity.ExchangeRateItem
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import io.reactivex.Completable
import io.reactivex.Observable

interface ExchangeRepo {

    fun startFetchData(pairs: List<ExchangePairDataModel>): Completable
    fun stopFetchData(): Completable
    fun getExchangeRate(): Observable<List<ExchangeRateItem>>
}
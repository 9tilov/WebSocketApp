package com.d9tilov.android.websockettestapp.main.domain

import com.d9tilov.android.websockettestapp.main.ui.entity.DataItem
import io.reactivex.Completable
import io.reactivex.Observable

interface ExchangeInteractor {

    fun startFetchData(): Completable
    fun stopFetchData(): Completable
    fun getExchangeRate(): Observable<List<DataItem.ExchangeRateUiItem>>
}
package com.d9tilov.android.websockettestapp.main.data.remote.parser

import com.d9tilov.android.websockettestapp.main.data.local.mapper.RatesMapper
import com.d9tilov.android.websockettestapp.main.data.remote.entity.ExchangeRemoteRates
import com.d9tilov.android.websockettestapp.main.data.remote.entity.SubscribedRemoteRates
import com.d9tilov.android.websockettestapp.main.domain.entity.ExchangeRateItem
import com.google.gson.Gson
import javax.inject.Inject

class WebSocketResponseParser @Inject constructor(
    private val gson: Gson,
    private val mapper: RatesMapper
) {

    fun getTicks(response: String): List<ExchangeRateItem> {
        return gson.fromJson(
            response,
            ExchangeRemoteRates::class.java
        ).rateRemotes?.map { mapper.to(it) } ?: emptyList()
    }

    fun getSubscribedElements(response: String): List<ExchangeRateItem> {
        val list = gson.fromJson(
            response,
            SubscribedRemoteRates::class.java
        ).subscribedList
        return list?.rateRemotes?.map { mapper.to(it) } ?: emptyList()
    }

    fun getSubscribedElementsCount(response: String): Int {
        return gson.fromJson(
            response,
            SubscribedRemoteRates::class.java
        ).subscribedNumber ?: -1
    }
}

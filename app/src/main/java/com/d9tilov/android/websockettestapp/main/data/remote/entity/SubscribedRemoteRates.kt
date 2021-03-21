package com.d9tilov.android.websockettestapp.main.data.remote.entity

import com.google.gson.annotations.SerializedName

data class SubscribedRemoteRates(
    @SerializedName("subscribed_count") val subscribedNumber: Int?,
    @SerializedName("subscribed_list") val subscribedList: ExchangeRemoteRates?
)

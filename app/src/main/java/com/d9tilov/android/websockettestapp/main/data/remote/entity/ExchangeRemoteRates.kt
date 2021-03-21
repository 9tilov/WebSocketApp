package com.d9tilov.android.websockettestapp.main.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ExchangeRemoteRates(@SerializedName("ticks") val rateRemotes: List<ExchangeRateRemoteItem>?)

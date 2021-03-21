package com.d9tilov.android.websockettestapp.main.data.remote.entity

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ExchangeRateRemoteItem(
    @SerializedName("s") val name: String,
    @SerializedName("b") val bid: BigDecimal,
    @SerializedName("bf") val bf: Int,
    @SerializedName("a") val ask: BigDecimal,
    @SerializedName("af") val af: Int,
    @SerializedName("spr") val spread: Double,
)

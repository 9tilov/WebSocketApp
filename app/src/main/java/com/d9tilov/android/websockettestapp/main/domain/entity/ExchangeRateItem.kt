package com.d9tilov.android.websockettestapp.main.domain.entity

import com.d9tilov.android.websockettestapp.currency.Currency
import java.math.BigDecimal

data class ExchangeRateItem(
    val id: Long,
    val from: Currency,
    val to: Currency,
    val bid: BigDecimal,
    val bf: Int,
    val ask: BigDecimal,
    val af: Int,
    val spread: Double
)

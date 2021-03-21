package com.d9tilov.android.websockettestapp.main.ui.entity

import com.d9tilov.android.websockettestapp.currency.Currency
import java.math.BigDecimal

sealed class DataItem {

    abstract val itemId: Long

    data class ExchangeRateUiItem(
        val id: Long,
        val from: Currency,
        val to: Currency,
        val bid: BigDecimal,
        val bf: Int,
        val ask: BigDecimal,
        val af: Int,
        val spread: Double
    ) : DataItem() {
        override val itemId = id
    }

    object Header : DataItem() {
        override val itemId = Long.MIN_VALUE
    }
}

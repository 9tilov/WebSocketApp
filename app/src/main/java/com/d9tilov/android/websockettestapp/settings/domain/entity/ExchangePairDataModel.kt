package com.d9tilov.android.websockettestapp.settings.domain.entity

import com.d9tilov.android.websockettestapp.currency.Currency

data class ExchangePairDataModel(
    val id: Long,
    val from: Currency,
    val to: Currency,
    val isActive: Boolean
)

package com.d9tilov.android.websockettestapp.main.data.local.mapper

import com.d9tilov.android.websockettestapp.base.data.mapper.Mapper
import com.d9tilov.android.websockettestapp.currency.Currency
import com.d9tilov.android.websockettestapp.main.data.remote.entity.ExchangeRateRemoteItem
import com.d9tilov.android.websockettestapp.main.domain.entity.ExchangeRateItem
import javax.inject.Inject

class RatesMapper @Inject constructor() :
    Mapper<ExchangeRateRemoteItem, ExchangeRateItem> {

    override fun to(model: ExchangeRateRemoteItem): ExchangeRateItem =
        with(model) {
            ExchangeRateItem(
                model.name.hashCode().toLong(),
                Currency.convertToCurrency(model.name.take(3)),
                Currency.convertToCurrency(model.name.takeLast(3)),
                bid,
                bf,
                ask,
                af,
                spread
            )
        }

    override fun from(model: ExchangeRateItem): ExchangeRateRemoteItem {
        TODO("Not yet implemented")
    }
}
package com.d9tilov.android.websockettestapp.main.ui.mapper

import com.d9tilov.android.websockettestapp.base.data.mapper.Mapper
import com.d9tilov.android.websockettestapp.main.domain.entity.ExchangeRateItem
import com.d9tilov.android.websockettestapp.main.ui.entity.DataItem
import javax.inject.Inject

class RatesUiMapper @Inject constructor() : Mapper<ExchangeRateItem, DataItem.ExchangeRateUiItem> {

    override fun to(model: ExchangeRateItem): DataItem.ExchangeRateUiItem =
        with(model) { DataItem.ExchangeRateUiItem(id, from, to, bid, bf, ask, af, spread) }

    override fun from(model: DataItem.ExchangeRateUiItem): ExchangeRateItem =
        with(model) { ExchangeRateItem(id, from, to, bid, bf, ask, af, spread) }
}

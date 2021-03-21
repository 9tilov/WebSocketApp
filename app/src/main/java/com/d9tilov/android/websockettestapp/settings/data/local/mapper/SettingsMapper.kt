package com.d9tilov.android.websockettestapp.settings.data.local.mapper

import com.d9tilov.android.websockettestapp.base.data.mapper.Mapper
import com.d9tilov.android.websockettestapp.currency.Currency
import com.d9tilov.android.websockettestapp.settings.data.local.entity.ExchangePairDbModel
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import javax.inject.Inject

class SettingsMapper @Inject constructor() :
    Mapper<ExchangePairDbModel, ExchangePairDataModel> {

    override fun to(model: ExchangePairDbModel): ExchangePairDataModel =
        with(model) {
            ExchangePairDataModel(
                id,
                Currency.convertToCurrency(from),
                Currency.convertToCurrency(to),
                isActive
            )
        }

    override fun from(model: ExchangePairDataModel): ExchangePairDbModel =
        with(model) {
            ExchangePairDbModel(
                id,
                from.name,
                to.name,
                isActive
            )
        }
}

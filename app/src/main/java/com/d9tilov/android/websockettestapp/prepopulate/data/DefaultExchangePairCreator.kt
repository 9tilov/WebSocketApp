package com.d9tilov.android.websockettestapp.prepopulate.data

import com.d9tilov.android.websockettestapp.currency.Currency
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DefaultExchangePairCreator @Inject constructor() {

    fun createDefaultExchangePairs(): List<ExchangePairDataModel> {
        return listOf(
            createExchangePair(
                1,
                Currency.BTC,
                Currency.USD
            ),
            createExchangePair(
                2,
                Currency.EUR,
                Currency.USD
            ),
            createExchangePair(
                3,
                Currency.EUR,
                Currency.GBR
            ),
            createExchangePair(
                4,
                Currency.USD,
                Currency.JPY
            ),
            createExchangePair(
                5,
                Currency.GBR,
                Currency.USD
            ),
            createExchangePair(
                6,
                Currency.USD,
                Currency.CHF
            ),
            createExchangePair(
                7,
                Currency.USD,
                Currency.CAD
            ),
            createExchangePair(
                8,
                Currency.AUD,
                Currency.USD
            ),
            createExchangePair(
                9,
                Currency.EUR,
                Currency.JPY
            ),
            createExchangePair(
                10,
                Currency.EUR,
                Currency.CHF
            )
        )
    }

    private fun createExchangePair(id: Long, from: Currency, to: Currency) = ExchangePairDataModel(
        id, from, to, true
    )
}
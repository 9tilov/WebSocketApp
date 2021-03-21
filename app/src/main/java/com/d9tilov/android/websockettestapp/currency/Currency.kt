package com.d9tilov.android.websockettestapp.currency

sealed class Currency(val name: String) {

    companion object {
        private const val USD_NAME = "USD"
        private const val BTC_NAME = "BTC"
        private const val EUR_NAME = "EUR"
        private const val GBR_NAME = "GBP"
        private const val JPY_NAME = "JPY"
        private const val CHF_NAME = "CHF"
        private const val CAD_NAME = "CAD"
        private const val AUD_NAME = "AUD"

        fun convertToCurrency(name: String): Currency {
            return when (name) {
                USD_NAME -> USD
                BTC_NAME -> BTC
                EUR_NAME -> EUR
                GBR_NAME -> GBR
                JPY_NAME -> JPY
                CHF_NAME -> CHF
                CAD_NAME -> CAD
                AUD_NAME -> AUD
                else -> throw IllegalArgumentException("Unknown currency name: $name")
            }
        }
    }

    object USD : Currency(USD_NAME)

    object BTC : Currency(BTC_NAME)

    object EUR : Currency(EUR_NAME)

    object GBR : Currency(GBR_NAME)

    object JPY : Currency(JPY_NAME)

    object CHF : Currency(CHF_NAME)

    object CAD : Currency(CAD_NAME)

    object AUD : Currency(AUD_NAME)
}

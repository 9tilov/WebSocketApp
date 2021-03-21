package com.d9tilov.android.websockettestapp.base.data.db.converters

import androidx.room.TypeConverter
import com.d9tilov.android.websockettestapp.currency.Currency

object CurrencyConverter {

    @TypeConverter
    @JvmStatic
    fun fromCurrencyToString(value: Currency): String {
        return value.name
    }

    @TypeConverter
    @JvmStatic
    fun toCurrencyFromString(value: String): Currency = Currency.convertToCurrency(value)
}
package com.d9tilov.android.websockettestapp.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.d9tilov.android.websockettestapp.base.data.db.converters.CurrencyConverter
import com.d9tilov.android.websockettestapp.settings.data.local.SettingsDao
import com.d9tilov.android.websockettestapp.settings.data.local.entity.ExchangePairDbModel

@Database(
    entities = [ExchangePairDbModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CurrencyConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
}

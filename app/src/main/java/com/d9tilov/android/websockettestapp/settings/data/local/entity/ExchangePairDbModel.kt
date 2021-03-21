package com.d9tilov.android.websockettestapp.settings.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class ExchangePairDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "from") val from: String,
    @ColumnInfo(name = "to") val to: String,
    @ColumnInfo(name = "isActive") val isActive: Boolean
)

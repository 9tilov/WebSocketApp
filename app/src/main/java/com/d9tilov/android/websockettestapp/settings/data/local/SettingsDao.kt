package com.d9tilov.android.websockettestapp.settings.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.d9tilov.android.websockettestapp.settings.data.local.entity.ExchangePairDbModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class SettingsDao {

    @Query("SELECT * FROM settings")
    abstract fun getList(): Flowable<List<ExchangePairDbModel>>

    @Query("SELECT * FROM settings WHERE isActive=1")
    abstract fun getActiveList(): Flowable<List<ExchangePairDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(model: ExchangePairDbModel): Completable

    @Update
    abstract fun update(model: ExchangePairDbModel): Completable

    @Query("SELECT COUNT(*)  FROM settings")
    abstract fun getCount(): Single<Int>
}

package com.d9tilov.android.websockettestapp.base.di

import android.content.Context
import androidx.room.Room
import com.d9tilov.android.websockettestapp.base.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }

    companion object {
        private const val DATABASE_NAME = "exchange-db"
    }
}

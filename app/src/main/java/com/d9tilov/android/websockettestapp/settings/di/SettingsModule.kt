package com.d9tilov.android.websockettestapp.settings.di

import com.d9tilov.android.websockettestapp.base.data.db.AppDatabase
import com.d9tilov.android.websockettestapp.base.socket.RxWebSocket
import com.d9tilov.android.websockettestapp.settings.data.ExchangeSettingsRepo
import com.d9tilov.android.websockettestapp.settings.data.local.mapper.SettingsMapper
import com.d9tilov.android.websockettestapp.settings.domain.ExchangeSettingsInteractor
import com.d9tilov.android.websockettestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.websockettestapp.settings.domain.SettingsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object SettingsModule {

    @Provides
    @ViewModelScoped
    fun provideSettingsRepo(
        appDatabase: AppDatabase,
        mapper: SettingsMapper,
        rxWebSocket: RxWebSocket
    ): SettingsRepo = ExchangeSettingsRepo(appDatabase, mapper, rxWebSocket)

    @Provides
    @ViewModelScoped
    fun provideExchangeInteractor(settingsRepo: SettingsRepo): SettingsInteractor =
        ExchangeSettingsInteractor(settingsRepo)
}

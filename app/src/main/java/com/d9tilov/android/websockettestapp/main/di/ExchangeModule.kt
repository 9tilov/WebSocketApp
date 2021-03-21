package com.d9tilov.android.websockettestapp.main.di

import com.d9tilov.android.websockettestapp.base.socket.RxWebSocket
import com.d9tilov.android.websockettestapp.main.data.ExchangeDataRepo
import com.d9tilov.android.websockettestapp.main.data.remote.parser.WebSocketResponseParser
import com.d9tilov.android.websockettestapp.main.domain.ExchangeCurrencyInteractor
import com.d9tilov.android.websockettestapp.main.domain.ExchangeInteractor
import com.d9tilov.android.websockettestapp.main.domain.ExchangeRepo
import com.d9tilov.android.websockettestapp.main.ui.mapper.RatesUiMapper
import com.d9tilov.android.websockettestapp.settings.domain.SettingsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object ExchangeModule {

    @Provides
    @ViewModelScoped
    fun provideExchangeRepo(
        rxWebSocket: RxWebSocket,
        parser: WebSocketResponseParser
    ): ExchangeRepo = ExchangeDataRepo(rxWebSocket, parser)

    @Provides
    @ViewModelScoped
    fun provideExchangeInteractor(
        exchangeRepo: ExchangeRepo,
        settingsInteractor: SettingsInteractor,
        mapper: RatesUiMapper
    ): ExchangeInteractor = ExchangeCurrencyInteractor(exchangeRepo, settingsInteractor, mapper)
}

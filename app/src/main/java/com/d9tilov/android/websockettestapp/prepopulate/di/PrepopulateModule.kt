package com.d9tilov.android.websockettestapp.prepopulate.di

import com.d9tilov.android.websockettestapp.base.data.db.AppDatabase
import com.d9tilov.android.websockettestapp.prepopulate.data.DefaultExchangePairCreator
import com.d9tilov.android.websockettestapp.prepopulate.data.PrepopulateDataRepo
import com.d9tilov.android.websockettestapp.prepopulate.domain.PrepopulateDataInteractor
import com.d9tilov.android.websockettestapp.prepopulate.domain.PrepopulateInteractor
import com.d9tilov.android.websockettestapp.prepopulate.domain.PrepopulateRepo
import com.d9tilov.android.websockettestapp.settings.data.local.mapper.SettingsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class PrepopulateModule {

    @Provides
    @ViewModelScoped
    fun providePrepopulateRepo(
        defaultExchangePairCreator: DefaultExchangePairCreator,
        appDatabase: AppDatabase,
        mapper: SettingsMapper
    ): PrepopulateRepo = PrepopulateDataRepo(defaultExchangePairCreator, appDatabase, mapper)

    @Provides
    @ViewModelScoped
    fun providePrepopulateInteractor(prepopulateRepo: PrepopulateRepo): PrepopulateInteractor =
        PrepopulateDataInteractor(prepopulateRepo)
}

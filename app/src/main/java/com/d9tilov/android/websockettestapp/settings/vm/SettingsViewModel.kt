package com.d9tilov.android.websockettestapp.settings.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d9tilov.android.websockettestapp.App.Companion.TAG
import com.d9tilov.android.websockettestapp.base.navigation.SettingsNavigator
import com.d9tilov.android.websockettestapp.base.vm.BaseViewModel
import com.d9tilov.android.websockettestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import com.d9tilov.android.websockettestapp.utils.addTo
import com.d9tilov.android.websockettestapp.utils.ioScheduler
import com.d9tilov.android.websockettestapp.utils.uiScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsInteractor: SettingsInteractor) :
    BaseViewModel<SettingsNavigator>() {

    private val exchangePairs = MutableLiveData<List<ExchangePairDataModel>>()

    init {
        settingsInteractor.getAllExchangeRatePairs()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({
                exchangePairs.value = it
            }, { Timber.tag(TAG).e(it) })
            .addTo(compositeDisposable)
    }

    fun updateExchangePair(item: ExchangePairDataModel) {
        settingsInteractor.updateSelection(item)
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({}, { Timber.tag(TAG).e(it) })
            .addTo(compositeDisposable)
    }

    fun getExchangePair(): LiveData<List<ExchangePairDataModel>> = exchangePairs
}

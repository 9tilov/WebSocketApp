package com.d9tilov.android.websockettestapp.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d9tilov.android.websockettestapp.App.Companion.TAG
import com.d9tilov.android.websockettestapp.base.navigation.MainNavigator
import com.d9tilov.android.websockettestapp.base.vm.BaseViewModel
import com.d9tilov.android.websockettestapp.main.domain.ExchangeInteractor
import com.d9tilov.android.websockettestapp.main.ui.entity.DataItem
import com.d9tilov.android.websockettestapp.prepopulate.domain.PrepopulateInteractor
import com.d9tilov.android.websockettestapp.utils.addTo
import com.d9tilov.android.websockettestapp.utils.ioScheduler
import com.d9tilov.android.websockettestapp.utils.uiScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeInteractor: ExchangeInteractor,
    prepopulateInteractor: PrepopulateInteractor
) : BaseViewModel<MainNavigator>() {

    private val exchangeRates = MutableLiveData<List<DataItem.ExchangeRateUiItem>>()

    init {
        prepopulateInteractor.prepopulateItemsIfNeed()
            .andThen(exchangeInteractor.startFetchData())
            .andThen(exchangeInteractor.getExchangeRate())
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .doOnError { navigator?.showError() }
            .retryWhen { it.flatMap { retryManager.observeRetries() } }
            .subscribe({ exchangeRates.value = it }, { Timber.tag(TAG).e(it) })
            .addTo(compositeDisposable)
    }

    fun openSettings() {
        navigator?.openSettings()
    }

    fun getExchangeRates(): LiveData<List<DataItem.ExchangeRateUiItem>> = exchangeRates

    override fun onCleared() {
        exchangeInteractor.stopFetchData()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe()
        super.onCleared()
    }
}
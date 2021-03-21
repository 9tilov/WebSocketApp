package com.d9tilov.android.websockettestapp.base.vm

import androidx.lifecycle.ViewModel
import com.d9tilov.android.websockettestapp.base.navigation.BaseNavigator
import com.d9tilov.android.websockettestapp.core.AndroidDisposable
import com.d9tilov.android.websockettestapp.core.NetworkRetryManager

abstract class BaseViewModel<T : BaseNavigator> : ViewModel() {

    var navigator: T? = null
        set(value) {
            field = value
            field?.let { onNavigatorAttached() }
        }
    protected val compositeDisposable = AndroidDisposable()
    protected val retryManager = NetworkRetryManager()

    fun retryCall() {
        retryManager.retry()
    }

    protected open fun onNavigatorAttached() {}

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}

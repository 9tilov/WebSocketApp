package com.d9tilov.android.websockettestapp.base.navigation

interface HomeNavigator : BaseNavigator

interface MainNavigator : BaseNavigator {
    fun openSettings()
    fun showError()
}

interface SettingsNavigator : BaseNavigator {
    fun showError()
}

package com.d9tilov.android.websockettestapp.main.ui

import com.d9tilov.android.websockettestapp.base.navigation.HomeNavigator
import com.d9tilov.android.websockettestapp.base.ui.BaseActivity
import com.d9tilov.android.websockettestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), HomeNavigator {

    override fun performDataBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}

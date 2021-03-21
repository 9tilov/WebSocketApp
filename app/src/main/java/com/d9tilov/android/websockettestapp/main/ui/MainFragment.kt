package com.d9tilov.android.websockettestapp.main.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d9tilov.android.websockettestapp.R
import com.d9tilov.android.websockettestapp.base.navigation.MainNavigator
import com.d9tilov.android.websockettestapp.base.ui.BaseFragment
import com.d9tilov.android.websockettestapp.base.ui.viewbinding.viewBinding
import com.d9tilov.android.websockettestapp.databinding.FragmentMainBinding
import com.d9tilov.android.websockettestapp.main.vm.MainViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainNavigator>(R.layout.fragment_main),
    MainNavigator {

    override val viewModel: MainViewModel by viewModels()
    override fun getNavigator(): MainNavigator = this

    private val exchangeRateAdapter = ExchangeRateAdapter()
    private val viewBinding by viewBinding(FragmentMainBinding::bind)
    private var toolbar: MaterialToolbar? = null

    override fun openSettings() {
        val action = MainFragmentDirections.toSettingsDest()
        findNavController().navigate(action)
    }

    override fun showError() {
        showSnackBarWithAction(
            getString(R.string.connection_error),
            getString(R.string.retry)
        ) { viewModel.retryCall() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        viewModel.getExchangeRates().observe(
            this.viewLifecycleOwner,
            { exchangeRateAdapter.updateItems(it) }
        )
        viewBinding.mainRv.adapter = exchangeRateAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        viewBinding.mainRv.layoutManager = layoutManager
        viewBinding.mainRv.itemAnimator = null
    }

    private fun initToolbar() {
        toolbar = viewBinding.mainToolbarContainer.mainToolbar
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        toolbar?.title = getString(R.string.app_name)
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    viewModel.openSettings()
                }
                else -> throw IllegalArgumentException("Unknown menu item with id: ${it.itemId}")
            }
            return@setOnMenuItemClickListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }
}
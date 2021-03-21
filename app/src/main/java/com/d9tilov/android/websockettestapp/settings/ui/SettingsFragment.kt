package com.d9tilov.android.websockettestapp.settings.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d9tilov.android.websockettestapp.R
import com.d9tilov.android.websockettestapp.base.events.OnItemClickListener
import com.d9tilov.android.websockettestapp.base.navigation.SettingsNavigator
import com.d9tilov.android.websockettestapp.base.ui.BaseFragment
import com.d9tilov.android.websockettestapp.base.ui.viewbinding.viewBinding
import com.d9tilov.android.websockettestapp.databinding.FragmentSettingsBinding
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import com.d9tilov.android.websockettestapp.settings.vm.SettingsViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsNavigator>(R.layout.fragment_settings),
    SettingsNavigator {

    override val viewModel: SettingsViewModel by viewModels()
    override fun getNavigator(): SettingsNavigator = this

    private val adapter = SettingsAdapter()
    private val viewBinding by viewBinding(FragmentSettingsBinding::bind)
    private var toolbar: MaterialToolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.itemClickListener = onItemClickListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        viewModel.getExchangePair().observe(
            this.viewLifecycleOwner,
            { adapter.updateItems(it) }
        )
        viewBinding.settingsRv.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        viewBinding.settingsRv.layoutManager = layoutManager
        viewBinding.settingsRv.itemAnimator = null
    }

    private fun initToolbar() {
        toolbar = viewBinding.settingsToolbarContainer.mainToolbar
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        toolbar?.title = getString(R.string.toolbar_name_settings)
        activity.setSupportActionBar(toolbar)
    }

    override fun showError() {
        showSnackBar(getString(R.string.connection_error))
    }

    private val onItemClickListener = object : OnItemClickListener<ExchangePairDataModel> {
        override fun onItemClick(item: ExchangePairDataModel, position: Int) {
            viewModel.updateExchangePair(item)
        }
    }
}
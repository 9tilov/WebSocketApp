package com.d9tilov.android.websockettestapp.settings.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel

class SettingsDiffUtil : DiffUtil.ItemCallback<ExchangePairDataModel>() {

    override fun areItemsTheSame(
        oldItem: ExchangePairDataModel,
        newItem: ExchangePairDataModel
    ): Boolean = oldItem.from.name == newItem.from.name && oldItem.to.name == oldItem.to.name

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ExchangePairDataModel,
        newItem: ExchangePairDataModel
    ): Boolean = oldItem == newItem
}

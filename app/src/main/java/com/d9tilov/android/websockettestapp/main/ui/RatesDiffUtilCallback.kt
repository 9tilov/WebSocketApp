package com.d9tilov.android.websockettestapp.main.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.d9tilov.android.websockettestapp.main.ui.entity.DataItem

class RatesDiffUtilCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem.itemId == newItem.itemId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem == newItem
}

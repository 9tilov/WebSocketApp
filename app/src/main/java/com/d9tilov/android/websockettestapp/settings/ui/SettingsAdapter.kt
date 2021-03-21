package com.d9tilov.android.websockettestapp.settings.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d9tilov.android.websockettestapp.R
import com.d9tilov.android.websockettestapp.base.events.OnItemClickListener
import com.d9tilov.android.websockettestapp.base.ui.BaseViewHolder
import com.d9tilov.android.websockettestapp.databinding.ItemExchangeSettingsBinding
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel

class SettingsAdapter :
    ListAdapter<ExchangePairDataModel, RecyclerView.ViewHolder>(SettingsDiffUtil()) {

    var itemClickListener: OnItemClickListener<ExchangePairDataModel>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingsViewHolder {
        val viewBinding =
            ItemExchangeSettingsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        val viewHolder = SettingsViewHolder(viewBinding)
        viewBinding.root.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                var item = getItem(adapterPosition)
                item = item.copy(isActive = !item.isActive)
                itemClickListener?.onItemClick(item, adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SettingsViewHolder -> {
                val item = getItem(position) as ExchangePairDataModel
                holder.bind(item)
            }
        }
    }

    fun updateItems(items: List<ExchangePairDataModel>) {
        submitList(items)
    }

    class SettingsViewHolder(private val viewBinding: ItemExchangeSettingsBinding) :
        BaseViewHolder(viewBinding) {

        fun bind(model: ExchangePairDataModel) {
            viewBinding.run {
                itemSettingsName.text =
                    context.getString(R.string.item_name, model.from.name, model.to.name)
                itemSettingsCheckbox.isChecked = model.isActive
            }
        }
    }
}
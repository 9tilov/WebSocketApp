package com.d9tilov.android.websockettestapp.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d9tilov.android.websockettestapp.R
import com.d9tilov.android.websockettestapp.base.ui.BaseViewHolder
import com.d9tilov.android.websockettestapp.databinding.ItemExchangeRateBinding
import com.d9tilov.android.websockettestapp.databinding.ItemExchangeRateHeaderBinding
import com.d9tilov.android.websockettestapp.main.ui.entity.DataItem

class ExchangeRateAdapter :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(RatesDiffUtilCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> RatesViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RatesViewHolder -> {
                val item = getItem(position) as DataItem.ExchangeRateUiItem
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ExchangeRateUiItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    fun updateItems(newRates: List<DataItem.ExchangeRateUiItem>) {
        val items = (when (newRates) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + newRates.map {
                DataItem.ExchangeRateUiItem(
                    it.id,
                    it.from,
                    it.to,
                    it.bid,
                    it.bf,
                    it.ask,
                    it.af,
                    it.spread
                )
            }
        })
        submitList(items)
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }

    class RatesViewHolder(private val viewBinding: ItemExchangeRateBinding) :
        BaseViewHolder(viewBinding) {

        fun bind(exchangeRateUiItem: DataItem.ExchangeRateUiItem) {
            viewBinding.run {
                itemRateName.text = context.getString(
                    R.string.item_name,
                    exchangeRateUiItem.from.name,
                    exchangeRateUiItem.to.name
                )
                itemRateBidAsk.text = context.getString(
                    R.string.item_bid_ask,
                    exchangeRateUiItem.bid.toString(),
                    exchangeRateUiItem.ask.toString()
                )
                itemRateSpread.text = exchangeRateUiItem.spread.toString()
            }
        }

        companion object {
            fun from(parent: ViewGroup): RatesViewHolder {
                val viewBinding =
                    ItemExchangeRateBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return RatesViewHolder(viewBinding)
            }
        }
    }

    class HeaderViewHolder(viewBinding: ItemExchangeRateHeaderBinding) :
        BaseViewHolder(viewBinding) {

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val viewBinding =
                    ItemExchangeRateHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return HeaderViewHolder(viewBinding)
            }
        }
    }
}

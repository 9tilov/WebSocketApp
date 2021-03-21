package com.d9tilov.android.websockettestapp.base.events

interface OnItemClickListener<in R> {
    fun onItemClick(item: R, position: Int = 0)
}

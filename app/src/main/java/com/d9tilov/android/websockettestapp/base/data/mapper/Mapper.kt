package com.d9tilov.android.websockettestapp.base.data.mapper

interface Mapper<T, E> {
    fun to(model: T): E
    fun from(model: E): T
}

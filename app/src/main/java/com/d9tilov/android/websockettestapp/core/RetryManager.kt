package com.d9tilov.android.websockettestapp.core

import io.reactivex.subjects.PublishSubject

interface RetryManager {
    fun observeRetries(): PublishSubject<RetryEvent>
    fun retry()
}
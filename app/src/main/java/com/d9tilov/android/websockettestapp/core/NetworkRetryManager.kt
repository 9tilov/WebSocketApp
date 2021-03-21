package com.d9tilov.android.websockettestapp.core

import io.reactivex.subjects.PublishSubject

class NetworkRetryManager : RetryManager {

    private val retrySubject = PublishSubject.create<RetryEvent>()

    override fun observeRetries(): PublishSubject<RetryEvent> {
        return retrySubject
    }

    override fun retry() {
        retrySubject.onNext(RetryEvent())
    }
}
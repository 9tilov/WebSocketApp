package com.d9tilov.android.websockettestapp.utils

import android.os.Looper
import androidx.annotation.RestrictTo
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
val ioScheduler: Scheduler = Schedulers.io()

@get:RestrictTo(RestrictTo.Scope.LIBRARY)
internal inline val isMainThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()

@RestrictTo(RestrictTo.Scope.LIBRARY)
internal fun checkIsMainThread() = check(isMainThread)

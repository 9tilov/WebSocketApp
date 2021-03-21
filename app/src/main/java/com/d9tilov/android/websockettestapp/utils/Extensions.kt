package com.d9tilov.android.websockettestapp.utils

import com.d9tilov.android.websockettestapp.core.AndroidDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(androidDisposable: AndroidDisposable): Disposable =
    apply { androidDisposable.add(this) }


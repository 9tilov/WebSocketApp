package com.d9tilov.android.websockettestapp.prepopulate.domain

import io.reactivex.Completable

interface PrepopulateInteractor {

    fun prepopulateItemsIfNeed(): Completable
}

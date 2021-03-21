package com.d9tilov.android.websockettestapp.prepopulate.domain

import io.reactivex.Completable

class PrepopulateDataInteractor(private val prepopulateRepo: PrepopulateRepo) :
    PrepopulateInteractor {

    override fun prepopulateItemsIfNeed(): Completable = prepopulateRepo.prepopulateItemsIfNeed()
}

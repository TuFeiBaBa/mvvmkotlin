package com.tufei.mvvmkotlin.util.rx

import io.reactivex.Single

/**
 * @author tufei
 * @date 2018/2/28.
 */
fun <T : Any> T.toSingle(): Single<T> {
    return Single.create<T> {
        it.onSuccess(this)
    }
}
package com.tufei.mvvmkotlin.util

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author tufei
 * @date 2018/2/26.
 */
fun <T> Observable<T>.ioToMain(): Observable<T> {
    return compose({ upstream ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    })
}

fun <T> Single<T>.ioToMain(): Single<T> {
    return compose({ upstream ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    })
}

fun <T : Any> T.toSingle(): Single<T> {
    return Single.create<T> {
        it.onSuccess(this)
    }
}

fun <T> Completable.ioToMain(): Completable {
    return compose({ upstream ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    })
}

private val onNextStub: (Any) -> Unit = {}
fun <T : Any> Single<T>.subscribeSuccess(
        onSuccess: (T) -> Unit = onNextStub
): Disposable = subscribe(onSuccess)
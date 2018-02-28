package com.tufei.mvvmkotlin.util.rx

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 线程切换
 * @author tufei
 * @date 2018/2/26.
 */
fun <T> Observable<T>.ioToMain(): Observable<T> {
    return compose {
        it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Single<T>.ioToMain(): Single<T> {
    return compose {
        it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Flowable<T>.ioToMain(): Flowable<T> {
    return compose { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
}

fun <T> Maybe<T>.ioToMain(): Maybe<T> {
    return compose { it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
}

fun Completable.ioToMain(): Completable {
    return compose {
        it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
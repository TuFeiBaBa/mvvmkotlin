package com.tufei.mvvmkotlin.util.rx

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions

/**
 * 封装了线程切换功能的subscribe方法。
 * 如果不需要带线程切换的，可以考虑使用rxkotlin相应的subscribeBy方法。
 * @author tufei
 * @date 2018/2/28.
 */
private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}
private fun <T : Any> ((T) -> Unit).asConsumer(): Consumer<T> {
    return if (this === onNextStub) Functions.emptyConsumer() else Consumer(this)
}

private fun ((Throwable) -> Unit).asOnErrorConsumer(): Consumer<Throwable> {
    return if (this === onErrorStub) Functions.ON_ERROR_MISSING else Consumer(this)
}

private fun (() -> Unit).asOnCompleteAction(): Action {
    return if (this === onCompleteStub) Functions.EMPTY_ACTION else Action(this)
}

fun <T : Any> Observable<T>.into(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Disposable = ioToMain().subscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

fun <T : Any> Flowable<T>.into(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Disposable = ioToMain().subscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

fun <T : Any> Single<T>.into(
        onError: (Throwable) -> Unit = onErrorStub,
        onSuccess: (T) -> Unit = onNextStub
): Disposable = ioToMain().subscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer())

fun <T : Any> Maybe<T>.into(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onSuccess: (T) -> Unit = onNextStub
): Disposable = ioToMain().subscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

fun Completable.into(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = when {
    onError === onErrorStub && onComplete === onCompleteStub -> ioToMain().subscribe()
    onError === onErrorStub -> ioToMain().subscribe(onComplete)
    else -> ioToMain().subscribe(onComplete.asOnCompleteAction(), Consumer(onError))
}

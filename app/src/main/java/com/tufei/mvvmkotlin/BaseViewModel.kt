package com.tufei.mvvmkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * @author tufei
 * @date 2018/3/2.
 */
open class BaseViewModel(context: Application) : AndroidViewModel(context) {
    val TAG = javaClass.simpleName
    protected val compositeDisposable = CompositeDisposable()
    val toastTip = SingleLiveEvent<Int>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
package com.tufei.mvvmkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable

/**
 * @author tufei
 * @date 2018/3/2.
 */
open class BaseViewModel(context: Application) : AndroidViewModel(context) {
    val TAG = javaClass.simpleName
    protected val compositeDisposable = CompositeDisposable()
    var toastTip = ObservableField<String>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
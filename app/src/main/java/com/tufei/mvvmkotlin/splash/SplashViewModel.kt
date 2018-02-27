package com.tufei.mvvmkotlin.splash

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.tufei.mvvmkotlin.adapter.Data
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author tufei
 * @date 2018/2/25.
 */
class SplashViewModel @Inject constructor(context: Application) : AndroidViewModel(context) {
    // These observable fields will update Views automatically
    var datas: ObservableList<Data> = ObservableArrayList()
    val compositeDisposable = CompositeDisposable()

    fun setData() {
        Observable.timer(6000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose {
                    Log.d("ViewModel", "dispose")
                }
                .subscribe({
                    Log.d("ViewModel", "onNext")
                    with(datas) {
                        clear()
                        val mutableListOf = mutableListOf(
                                Data("a", "man"),
                                Data("b", "woman"),
                                Data("d", "man"))
                        addAll(mutableListOf)
                    }
                }, {
                    Log.e("ViewModel", "onError:" + it.message)
                })
                .addTo(compositeDisposable)
        Observable.timer(12000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose {
                    Log.d("ViewModel", "dispose")
                }
                .subscribe({
                    Log.d("ViewModel", "onNext")
                    with(datas) {
                        val mutableListOf = mutableListOf(Data(
                                "a", "woman"),
                                Data("b", "man"),
                                Data("c", "man"))
                        clear()
                        datas.addAll(mutableListOf)
                    }
                }, {
                    Log.e("ViewModel", "onError:" + it.message)
                })
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
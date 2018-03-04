package com.tufei.mvvmkotlin.test.adapter

import android.app.Application
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.tufei.mvvmkotlin.BaseViewModel
import com.tufei.mvvmkotlin.util.rx.into
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author tufei
 * @date 2018/3/2.
 */
class AdapterViewModel @Inject constructor(context: Application) : BaseViewModel(context) {
    var datas: ObservableList<TestData> = ObservableArrayList()
    fun setData() {
        Observable.timer(6000, TimeUnit.MILLISECONDS)
                .into {
                    Log.d(TAG, "onNext")
                    with(datas) {
                        clear()
                        val mutableListOf = mutableListOf(
                                TestData("a", "man"),
                                TestData("b", "woman"),
                                TestData("d", "man"))
                        addAll(mutableListOf)
                    }
                }
                .addTo(compositeDisposable)
        Observable.timer(12000, TimeUnit.MILLISECONDS)
                .into {
                    with(datas) {
                        val mutableListOf = mutableListOf(
                                TestData("a", "woman"),
                                TestData("b", "man"),
                                TestData("c", "man"))
                        clear()
                        datas.addAll(mutableListOf)
                    }
                }
                .addTo(compositeDisposable)
    }
}
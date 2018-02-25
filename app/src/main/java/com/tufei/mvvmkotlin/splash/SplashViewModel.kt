package com.tufei.mvvmkotlin.splash

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.os.Handler
import com.tufei.mvvmkotlin.adapter.Data

/**
 * @author tufei
 * @date 2018/2/25.
 */
class SplashViewModel(context: Application) : AndroidViewModel(context) {
    // These observable fields will update Views automatically
    val datas: ObservableList<Data> = ObservableArrayList()

    fun setData() {
        Handler().postDelayed({
            with(datas){
                clear()
                val mutableListOf = mutableListOf(Data("a"), Data("b"))
                addAll(mutableListOf)
            }
        },3000)
    }
}
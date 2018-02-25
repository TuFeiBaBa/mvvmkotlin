package com.tufei.mvvmkotlin.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.tufei.mvvmkotlin.ViewModelFactory

/**
 * @author tufei
 * @date 2018/2/25.
 */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)
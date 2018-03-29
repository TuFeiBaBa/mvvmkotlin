package com.tufei.mvvmkotlin

import com.tufei.mvvmkotlin.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * @author TuFei
 * @date 2018/3/29.
 */
class TestApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out dagger.android.DaggerApplication> =
            DaggerAppComponent.builder().application(this).build()
}
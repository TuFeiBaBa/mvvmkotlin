package com.tufei.mvvmkotlin

import android.app.ActivityManager
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.tufei.mvvmkotlin.di.DaggerAppComponent
import com.tufei.mvvmkotlin.util.Preferences
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

/**
 * @author tufei
 * @date 2018/2/20.
 */
class App : DaggerApplication() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        //有多个进程的时候，会初始化多次
        if (isMainProcess()) {
            init()
        }
    }

    private fun init() {
        //初始化sp
        Preferences.initSharedPreferences(this)
    }

    private fun isMainProcess(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = android.os.Process.myPid()

        return processInfos.any { it.pid == myPid && mainProcessName == it.processName }
    }
}
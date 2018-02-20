package com.tufei.mvvmkotlin

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.tufei.mvvmkotlin.util.Sp

/**
 * @author tufei
 * @date 2018/2/20.
 */
class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        if(isMainProcess()){
            init()
        }
    }

    private fun init() {
        //初始化sp
        Sp.initSharedPreferences(this)
    }

    private fun isMainProcess(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = android.os.Process.myPid()

        return processInfos.any { it.pid == myPid && mainProcessName == it.processName }
    }
}
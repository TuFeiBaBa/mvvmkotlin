package com.tufei.mvvmkotlin

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers


/**
 * @author tufei
 * @date 2018/2/26.
 */
object RxJava {

    /**
     * 单元测试的时候，利用RxJavaPlugins将io线程转换为trampoline
     * trampoline应该是立即执行的意思（待商榷），替代了Rx1的immediate。
     */
    fun asyncToSync() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { _ -> Schedulers.trampoline() }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }
}
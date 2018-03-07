package com.tufei.mvvmkotlin.testutil

import android.app.Application
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * 基类。当需要使用robolectric测试时，继承该类即可。
 * 注意:
 * 1)这里不会再初始化我们自定义的Application。不用再担心自定义的Application里面的代码，
 * 会干扰测试。
 * 2)定义了[robolectricRule]:开启了控制台日志输出。将RxJava的异步，变成同步。
 * @author tufei
 * @date 2018/3/7.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
open class RobolectricTest {
    @get:Rule
    val robolectricRule = RobolectricRule()
}
package com.tufei.mvvmkotlin.testutil

import android.app.Application
import org.junit.Rule
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * 基类。当需要使用robolectric+powermock测试时，继承该类即可。
 * 注意：继承该类后，在使用PowerMock来mock一个类前，记得使用
 * @PrepareForTest(YourClassName::class) 标注继承的类
 *
 * @author tufei
 * @date 2018/3/7.
 */
@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore(value = ["org.mockito.*", "org.robolectric.*", "android.*"])
@Config(application = Application::class)
open class PowerWithRobolectricTest {
    @get:Rule
    var rule = PowerMockRule()
    @get:Rule
    val robolectricRule = RobolectricRule()
}
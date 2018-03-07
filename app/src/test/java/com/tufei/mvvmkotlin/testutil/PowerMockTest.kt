package com.tufei.mvvmkotlin.testutil

import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

/**
 * 基类。当需要使用PowerMock测试时，继承该类即可。
 * 注意：继承该类后，在使用PowerMock来mock一个类前，记得使用
 * @PrepareForTest(YourClassName::class) 标注继承的类
 *
 * @author tufei
 * @date 2018/3/7.
 */
@RunWith(PowerMockRunner::class)
open class PowerMockTest
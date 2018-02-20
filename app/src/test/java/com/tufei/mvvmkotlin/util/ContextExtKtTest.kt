package com.tufei.mvvmkotlin.util

import android.widget.Toast
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowToast
import java.lang.IllegalArgumentException

/**
 * @author tufei
 * @date 2018/2/20.
 */
@RunWith(RobolectricTestRunner::class)
class ContextExtKtTest {
    private val context = RuntimeEnvironment.application

    @Test
    fun showToastNormal() {
        context.showToast("hello")
        Assert.assertEquals("hello", ShadowToast.getTextOfLatestToast())

        context.showToast("world", Toast.LENGTH_LONG)
        Assert.assertEquals("world", ShadowToast.getTextOfLatestToast())
    }

    @Test(expected = IllegalArgumentException::class)
    fun showToastByIllegalArgument() {
        context.showToast("", -1)
    }
}
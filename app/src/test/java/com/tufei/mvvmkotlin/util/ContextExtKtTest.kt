package com.tufei.mvvmkotlin.util

import android.widget.Toast
import com.tufei.mvvmkotlin.RxJava
import io.reactivex.rxkotlin.toObservable
import junit.framework.Assert.assertEquals
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
        assertEquals("hello", ShadowToast.getTextOfLatestToast())

        context.showToast("world", Toast.LENGTH_LONG)
        assertEquals("world", ShadowToast.getTextOfLatestToast())
    }

    @Test(expected = IllegalArgumentException::class)
    fun showToastByIllegalArgument() {
        context.showToast("", -1)
    }

    @Test fun test(){
        RxJava.asyncToSync()
        arrayListOf("name","b","c")
                .toObservable()
                .ioToMain()
                .test()
                .assertValues("name","b","c")
    }
}
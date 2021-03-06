package com.tufei.mvvmkotlin.util

import com.tufei.mvvmkotlin.R
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowToast
import java.io.FileNotFoundException

/**
 * @author tufei
 * @date 2018/2/20.
 */
@RunWith(RobolectricTestRunner::class)
class ContextExtTest {
    private val context = RuntimeEnvironment.application

    @Test
    fun showToastNormal() {
        context.showToast(R.string.app_name)
        assertEquals(context.getString(R.string.app_name), ShadowToast.getTextOfLatestToast())

        context.showToast(R.string.app_name, ToastTime.LONG)
        assertEquals(context.getString(R.string.app_name), ShadowToast.getTextOfLatestToast())
    }


    @Test
    fun readAssetsFileNormal() {
        val actual = context.readAssetsFile("test.txt")
        assertEquals("test", actual)
    }

    @Test(expected = FileNotFoundException::class)
    fun readAssetsFileWithNotExistFile() {
        val actual = context.readAssetsFile("NotExistFile")
        assertEquals("test", actual)
    }

    @Test
    fun readRawFileNormal() {
        val bytes = context.readRawFile(R.raw.test)
        val actual = String(bytes)
        assertEquals("test", actual)
    }
}
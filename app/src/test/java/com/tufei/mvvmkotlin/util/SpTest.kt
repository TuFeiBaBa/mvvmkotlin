package com.tufei.mvvmkotlin.util

import com.tufei.mvvmkotlin.BuildConfig
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * @author tufei
 * @date 2018/2/21.
 */
@RunWith(RobolectricTestRunner::class)
class SpTest {
    private var boolean: Boolean by Sp("boolean", false)
    private var string: String by Sp("string", "")

    @Before
    fun setup(){
        Sp.initSharedPreferences(RuntimeEnvironment.application)
    }

    @Test
    fun testSp() {
        assertFalse(boolean)
        boolean = true
        assertTrue(boolean)

        assertEquals("",string)
        string = "message"
        assertEquals("message",string)
    }
}
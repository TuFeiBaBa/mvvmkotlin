package com.tufei.mvvmkotlin.util

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
class PreferencesTest {
    private var boolean: Boolean by Preferences("boolean", false)
    private var string: String by Preferences("string", "")

    @Before
    fun setup(){
        Preferences.initSharedPreferences(RuntimeEnvironment.application)
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
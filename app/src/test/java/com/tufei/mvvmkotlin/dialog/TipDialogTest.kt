package com.mawaig.taxrobot.dialog

import android.app.Application
import android.view.View
import android.widget.Toast
import com.tufei.mvvmkotlin.dialog.TipDialog
import kotlinx.android.synthetic.main.dialog_fragment.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment
import org.robolectric.util.FragmentTestUtil.startFragment

/**
 * @author tufei
 * @date 2018/3/5.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class TipDialogTest {

    private val tip = "点击成功！"
    private val action: (View) -> Unit = {
        Toast.makeText(RuntimeEnvironment.application, tip, Toast.LENGTH_LONG).show()
    }

    @Test
    fun testPositiveClick() {
        val dialog = TipDialog()
                .setPositiveClick {
                    action(it)
                }
        startFragment(dialog)
        dialog.btn_positive.performClick()
        val actual = ShadowToast.getTextOfLatestToast()
        assertEquals(tip, actual)
    }
}
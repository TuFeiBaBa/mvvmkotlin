package com.tufei.mvvmkotlin.test.aop

import android.os.Bundle
import android.util.Log
import com.tufei.mvvmkotlin.BaseActivity
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.aop.ClickCheck
import kotlinx.android.synthetic.main.click_check_activity.*

/**
 * @author tufei
 * @date 2018/3/4.
 */
class ClickCheckActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.click_check_activity)
        btn.setOnClickListener {
            noTipOnDuplicateClick()
        }
        btn_tip.setOnClickListener {
            withTipOnDuplicateClick()
        }
    }

    @ClickCheck(tag = 1)
    private fun noTipOnDuplicateClick() {
        Log.d(TAG,"noTipOnDuplicateClick")
    }

    @ClickCheck("请勿重复点击",tag = 2)
    private fun withTipOnDuplicateClick() {
        Log.d(TAG,"withTipOnDuplicateClick")
    }
}
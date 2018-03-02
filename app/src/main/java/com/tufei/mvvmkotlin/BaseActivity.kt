package com.tufei.mvvmkotlin

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.tufei.architecturedemo.util.ActivityCollector
import dagger.android.support.DaggerAppCompatActivity

/**
 * @author tufei
 * @date 2018/2/19.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {
    val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    inline fun <reified T : ViewDataBinding> setLayout(@LayoutRes layoutId: Int): T {
        return DataBindingUtil.setContentView(this, layoutId)
    }

    inline fun <reified T : Activity> startActivity(bundle: Bundle? = null) {
        val intent = Intent(this, T::class.java).apply {
            if (bundle != null) {
                putExtras(bundle)
            }
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        ActivityCollector.removeActivity(this)
        super.onDestroy()
    }
}
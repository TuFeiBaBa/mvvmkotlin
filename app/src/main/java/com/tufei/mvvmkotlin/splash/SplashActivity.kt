package com.tufei.mvvmkotlin.splash

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.databinding.SplashActivityBinding
import dagger.android.support.DaggerAppCompatActivity


class SplashActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<SplashActivityBinding>(this, R.layout.splash_activity)
    }

}

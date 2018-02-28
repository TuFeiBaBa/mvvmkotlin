package com.tufei.mvvmkotlin.splash

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.adapter.TestAdapter
import com.tufei.mvvmkotlin.aop.NetCheck
import com.tufei.mvvmkotlin.databinding.SplashActivityBinding
import com.tufei.mvvmkotlin.util.getViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.splash_activity.*


class SplashActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashActivityBinding = DataBindingUtil.setContentView<SplashActivityBinding>(this, R.layout.splash_activity)
        val testAdapter = TestAdapter()
        recyclerView.adapter = testAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        val viewModel = getViewModel(SplashViewModel::class.java)
        splashActivityBinding.viewmodel = viewModel
        splashActivityBinding.viewmodel?.setData()
        button.setOnClickListener {
            test()
        }
    }

    @NetCheck
    fun test() {
        Log.d("Tag", "splash")
    }
}

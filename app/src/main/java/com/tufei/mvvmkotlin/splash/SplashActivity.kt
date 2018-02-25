package com.tufei.mvvmkotlin.splash

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.adapter.TestAdapter
import com.tufei.mvvmkotlin.databinding.SplashActivityBinding
import com.tufei.mvvmkotlin.util.obtainViewModel
import kotlinx.android.synthetic.main.splash_activity.*



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashActivityBinding = DataBindingUtil.setContentView<SplashActivityBinding>(this, R.layout.splash_activity)
        val testAdapter = TestAdapter()
        recyclerView.adapter = testAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        val viewModel = obtainViewModel(SplashViewModel::class.java)
        splashActivityBinding.viewmodel = viewModel
        splashActivityBinding.viewmodel?.setData()
    }
}

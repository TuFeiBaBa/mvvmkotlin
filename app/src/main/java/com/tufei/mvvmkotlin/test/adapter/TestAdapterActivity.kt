package com.tufei.mvvmkotlin.test.adapter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.tufei.mvvmkotlin.BaseActivity
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.adapter.OnItemClickListener
import com.tufei.mvvmkotlin.databinding.TestAdapterActivityBinding
import com.tufei.mvvmkotlin.util.getViewModel
import kotlinx.android.synthetic.main.test_adapter_activity.*

/**
 * @author tufei
 * @date 2018/3/2.
 */
class TestAdapterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setLayout<TestAdapterActivityBinding>(R.layout.test_adapter_activity)
        with(recyclerView) {
            adapter = SimpleAdapter().apply {
                itemListener = object : OnItemClickListener<TestData> {
                    override fun onClick(view: View, data: TestData) {
                        val position = view.tag as? Int
                        Log.d(TAG, "position:$position,data:$data")
                    }
                }
            }
            layoutManager = LinearLayoutManager(this@TestAdapterActivity)
        }

        with(specialRecyclerView) {
            adapter = SimpleSpecialAdapter().apply {
                itemListener = object : OnItemClickListener<TestData> {
                    override fun onClick(view: View, data: TestData) {
                        val position = view.tag as? Int
                        Log.d(TAG, "special:position:$position,data:$data")
                    }
                }
            }
            layoutManager = LinearLayoutManager(this@TestAdapterActivity)
        }

        val viewModel = getViewModel(AdapterViewModel::class.java)
        binding.viewmodel = viewModel
        binding.viewmodel?.setData()
    }

}
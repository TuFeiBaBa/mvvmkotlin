package com.tufei.mvvmkotlin.test

import android.util.Log
import android.view.View.GONE
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.adapter.BaseSpecialAdapter
import com.tufei.mvvmkotlin.adapter.OnItemRemoveListener
import com.tufei.mvvmkotlin.databinding.SimpleSpecialItemBinding

/**
 * @author tufei
 * @date 2018/3/3.
 */
class SimpleSpecialAdapter :
        BaseSpecialAdapter<TestData, SimpleSpecialItemBinding>(R.layout.simple_special_item) {
    override fun onBindSpecial(binding: SimpleSpecialItemBinding) {
        with(binding) {
            root.setOnClickListener {
                Log.d(TAG, "add item")
            }
            tvName.text = "添加用户"
            btnDelete.visibility = GONE
        }
    }

    override fun onBind(binding: SimpleSpecialItemBinding, data: TestData, position: Int) {
        with(binding) {
            listener = itemListener
            this.data = data
            root.tag = position
            removeListener = object : OnItemRemoveListener<TestData> {
                override fun onRemove(data: TestData) {
                    removeItem(data)
                }
            }
        }
    }
}
package com.tufei.mvvmkotlin.adapter

import android.databinding.DataBindingComponent
import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.databinding.TestItemBinding

/**
 * @author tufei
 * @date 2018/2/21.
 */
class TestAdapter(
        dataBindingComponent: DataBindingComponent,
        layoutId: Int = R.layout.test_item)
    : BaseAdapter<Data, TestItemBinding>(dataBindingComponent, layoutId) {

    override fun bind(binding: TestItemBinding, data: Data) {
        binding.data = data
    }
}
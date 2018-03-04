package com.tufei.mvvmkotlin.test

import com.tufei.mvvmkotlin.R
import com.tufei.mvvmkotlin.adapter.BaseAdapter
import com.tufei.mvvmkotlin.adapter.OnItemRemoveListener
import com.tufei.mvvmkotlin.databinding.SimpleItemBinding

/**
 * @author tufei
 * @date 2018/3/2.
 */
class SimpleAdapter : BaseAdapter<TestData, SimpleItemBinding>(R.layout.simple_item) {

    override fun onBind(binding: SimpleItemBinding, data: TestData, position: Int) {
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

    override fun areItemsTheSame(oldItem: TestData, newItem: TestData) = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: TestData, newItem: TestData) = oldItem.sex == newItem.sex
}
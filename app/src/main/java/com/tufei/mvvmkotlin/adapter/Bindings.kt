package com.tufei.mvvmkotlin.adapter

import android.databinding.BindingAdapter
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * @author tufei
 * @date 2018/3/2.
 */
object Bindings {
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("datas")
    @JvmStatic
    fun <T> setDatas(recyclerView: RecyclerView, datas: MutableList<T>) {
        val adapter =
                recyclerView.adapter as? BaseAdapter<T, ViewDataBinding>
                        ?: throw NullPointerException("RecyclerView must set Adapter!")
        adapter.datas = datas
    }
}
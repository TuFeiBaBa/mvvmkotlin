package com.tufei.mvvmkotlin.adapter

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR

/**
 * @author tufei
 * @date 2018/2/21.
 */
abstract class BaseAdapter<T, in R : ViewDataBinding>(
        @LayoutRes private val layoutId: Int)
    : RecyclerView.Adapter<ViewHolder>() {

    var datas = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<R>(inflater, layoutId, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = datas.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(BR.data, datas[position])
        holder.binding.executePendingBindings()
    }
}

object Bindings {
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("app:datas")
    @JvmStatic
    fun <T> setDatas(recyclerView: RecyclerView, datas: MutableList<T>) {
        with(recyclerView.adapter as BaseAdapter<T, ViewDataBinding>) {
            this.datas = datas
        }
    }
}

class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
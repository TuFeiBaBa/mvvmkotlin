package com.tufei.mvvmkotlin.adapter

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author tufei
 * @date 2018/2/21.
 */
abstract class BaseAdapter<T, in R : ViewDataBinding>(
        private val dataBindingComponent: DataBindingComponent,
        @LayoutRes private val layoutId: Int)
    : RecyclerView.Adapter<ViewHolder>() {

    val datas = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<R>(inflater, layoutId, parent, false, dataBindingComponent)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = datas.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder.binding as R, datas[position])
        holder.binding.executePendingBindings()
    }

    abstract fun bind(binding: R, data: T)
}

class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
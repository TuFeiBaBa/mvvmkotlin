package com.tufei.mvvmkotlin.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 项目里，有个特殊的情况经常出现，RecyclerView是网格布局，且第一个条目
 * 要特殊对待：第一个条目是固定存在的点击添加条目
 * @author tufei
 * @date 2018/3/2.
 */
abstract class BaseSpecialAdapter<T, in R : ViewDataBinding>(
        @LayoutRes private val layoutId: Int)
    : BaseAdapter<T, R>(layoutId) {

    override var datas = mutableListOf<T>()
        /**
         * 不能再像[BaseAdapter]那样使用[DiffUtil.DiffResult]做了一定的优化处理,
         * 不然datas有变更，会出现第一个条目的消失Bug
         */
        set(update) {
            field.clear()
            field.addAll(update)
            notifyDataSetChanged()
        }

    override fun addItem(data: T) {
        datas.add(data)
        notifyItemChanged(datas.size)
    }

    override fun removeItem(data: T) {
        if (datas.contains(data)) {
            var position = datas.indexOf(data)
            datas.remove(data)
            position++
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, datas.size + 1 - position + 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<R>(inflater, layoutId, parent, false)
        return ViewHolder(binding, binding.root)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            onBindSpecial(holder.binding as R)
        } else {
            onBind(holder.binding as R, datas[position - 1], position - 1)
        }
        holder.binding.executePendingBindings()
    }

    abstract fun onBindSpecial(binding: R)

    override fun getItemCount(): Int = datas.size + 1

    override fun areItemsTheSame(oldItem: T, newItem: T) = false

    override fun areContentsTheSame(oldItem: T, newItem: T) = false
}
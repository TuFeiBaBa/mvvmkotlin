package com.tufei.mvvmkotlin.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author tufei
 * @date 2018/3/2.
 */
/**
 * 项目里，有个特殊的情况经常出现，RecyclerView是网格布局，且第一个条目
 * 要特殊对待：第一个条目是固定存在的点击添加条目
 * @author tufei
 * @date 2018/2/21
 */
abstract class SpecialAdapter<T, in R : ViewDataBinding>(
        @LayoutRes private val layoutId: Int)
    : BaseAdapter<T, R>(layoutId) {

    override var datas = mutableListOf<T>()
        /**
         * 使用[DiffUtil.DiffResult]做了一定的优化处理，但需要你在你的adapter里面，实现
         * 属于你自己的[areItemsTheSame]和[areContentsTheSame]方法，才能实现优化
         */
        set(update) {
            replace(field, update)
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
        onBind(holder.binding as R, position)
        holder.binding.executePendingBindings()
    }

    /**
     * 本质就是adapter的[onBindViewHolder]
     */
    abstract fun onBind(binding: R, position: Int)

    override fun getItemCount(): Int = datas.size + 1
}
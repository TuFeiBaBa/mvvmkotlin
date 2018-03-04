package com.tufei.mvvmkotlin.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tufei.mvvmkotlin.util.rx.into
import com.tufei.mvvmkotlin.util.rx.toSingle


/**
 * @param layoutId  RecyclerView的条目布局id
 *
 * @author tufei
 * @date 2018/2/21.
 */
abstract class BaseAdapter<T, in R : ViewDataBinding>(
        @LayoutRes private val layoutId: Int)
    : RecyclerView.Adapter<ViewHolder>() {
    protected val TAG = javaClass.simpleName
    var itemListener: OnItemClickListener<T>? = null
    var itemRemoveListener: OnItemRemoveListener<T>? = null
    var itemAddListener: OnItemAddListener<T>? = null

    open var datas = mutableListOf<T>()
        /**
         * 使用[DiffUtil.DiffResult]做了一定的优化处理，但需要你在你的adapter里面，实现
         * 属于你自己的[areItemsTheSame]和[areContentsTheSame]方法，才能实现优化
         */
        set(update) {
            replace(field, update)
        }

    open fun addItem(data: T) {
        datas.add(data)
        notifyItemChanged(datas.size - 1)
    }

    open fun removeItem(data: T) {
        if (datas.contains(data)) {
            val position = datas.indexOf(data)
            datas.remove(data)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, datas.size - position + 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<R>(inflater, layoutId, parent, false)
        return ViewHolder(binding, binding.root)
    }

    override fun getItemCount(): Int = datas.size

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder.binding as R, datas[position], position)
        holder.binding.executePendingBindings()
    }

    /**
     * 本质就是adapter的[onBindViewHolder]
     */
    abstract fun onBind(binding: R, data: T, position: Int)

    protected fun replace(old: MutableList<T>, update: MutableList<T>) {
        when {
            old.isEmpty() -> {
                old.addAll(update)
                notifyDataSetChanged()
            }
            update.isEmpty() -> {
                old.clear()
                notifyDataSetChanged()
            }
            else -> {
                update(old, update)
                        .toSingle()
                        .into {
                            old.clear()
                            old.addAll(update)
                            it.dispatchUpdatesTo(this)
                        }
            }
        }
    }

    private fun update(oldItems: List<T>, update: List<T>): DiffUtil.DiffResult =
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize() = oldItems.size

                override fun getNewListSize() = update.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                        areContentsTheSame(oldItems[oldItemPosition], update[newItemPosition])

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                        areItemsTheSame(oldItems[oldItemPosition], update[newItemPosition])
            })

    protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    protected abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}
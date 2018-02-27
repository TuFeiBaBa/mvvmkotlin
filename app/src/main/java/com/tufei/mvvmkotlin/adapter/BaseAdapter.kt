package com.tufei.mvvmkotlin.adapter

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tufei.mvvmkotlin.BR
import com.tufei.mvvmkotlin.util.ioToMain
import com.tufei.mvvmkotlin.util.subscribeSuccess
import com.tufei.mvvmkotlin.util.toSingle


/**
 * @author tufei
 * @date 2018/2/21.
 */
abstract class BaseAdapter<T, in R : ViewDataBinding>(
        @LayoutRes private val layoutId: Int)
    : RecyclerView.Adapter<ViewHolder>() {
    var datas = mutableListOf<T>()
        set(update) {
            replace(field, update)
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

    private fun replace(old: MutableList<T>, update: MutableList<T>) {
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
                        .ioToMain()
                        .subscribeSuccess {
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

    protected open fun areItemsTheSame(oldItem: T, newItem: T) = false

    protected open fun areContentsTheSame(oldItem: T, newItem: T) = false
}

object Bindings {
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("datas")
    @JvmStatic
    fun <T> setDatas(recyclerView: RecyclerView, datas: MutableList<T>) {
        with(recyclerView.adapter as BaseAdapter<T, ViewDataBinding>) {
            this.datas = datas
        }
    }
}

class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
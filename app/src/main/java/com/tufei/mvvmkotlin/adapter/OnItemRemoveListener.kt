package com.tufei.mvvmkotlin.adapter

/**
 * @author tufei
 * @date 2018/3/2.
 */
interface OnItemRemoveListener<T> {
    fun onRemove(data: T)
}
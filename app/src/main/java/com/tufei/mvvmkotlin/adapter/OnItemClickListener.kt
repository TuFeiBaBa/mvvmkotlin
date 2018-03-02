package com.tufei.mvvmkotlin.adapter

import android.view.View

/**
 * @author tufei
 * @date 2018/3/1.
 */
interface OnItemClickListener<T> {
    fun onClick(view : View, data: T)
}
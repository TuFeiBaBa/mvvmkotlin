package com.tufei.mvvmkotlin.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

/**
 * @author tufei
 * @date 2018/3/2.
 */
class ViewHolder(val binding: ViewDataBinding, override val containerView: View)
    : RecyclerView.ViewHolder(binding.root), LayoutContainer
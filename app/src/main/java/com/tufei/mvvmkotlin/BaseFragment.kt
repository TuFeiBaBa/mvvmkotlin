package com.tufei.mvvmkotlin

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tufei.mvvmkotlin.util.showToast
import dagger.android.support.DaggerFragment

/**
 * @author tufei
 * @date 2018/2/21.
 */
abstract class BaseFragment<T : BaseViewModel> : DaggerFragment() {
    val TAG: String = javaClass.simpleName
    lateinit var viewModel: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<ViewDataBinding>(inflater, setContentView(),
                    container, false).root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = setViewModel()
        setupToast()
    }

    abstract fun setViewModel(): T

    @LayoutRes
    abstract fun setContentView(): Int

    private fun setupToast() {
        context?.showToast(this,viewModel.toastTip)
    }
}
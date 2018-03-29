package com.tufei.mvvmkotlin

import android.databinding.DataBindingUtil
import android.databinding.Observable
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
    private lateinit var toastCallback: Observable.OnPropertyChangedCallback
    lateinit var viewModel: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<ViewDataBinding>(inflater, setContentView(),
                    container, false).root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = setViewModel()
        setupToast()
    }

    override fun onDestroy() {
        viewModel.toastTip.removeOnPropertyChangedCallback(toastCallback)
        super.onDestroy()
    }

    abstract fun setViewModel(): T

    @LayoutRes
    abstract fun setContentView(): Int

    private fun setupToast() {
        toastCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                val tip = viewModel.toastTip.get()
                context?.showToast(tip)
            }
        }
        viewModel.toastTip.addOnPropertyChangedCallback(toastCallback)
    }
}
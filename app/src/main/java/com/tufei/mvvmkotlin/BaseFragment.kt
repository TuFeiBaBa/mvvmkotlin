package com.tufei.mvvmkotlin

import dagger.android.support.DaggerFragment

/**
 * @author tufei
 * @date 2018/2/21.
 */
class BaseFragment : DaggerFragment() {
    val TAG: String = javaClass.simpleName
}
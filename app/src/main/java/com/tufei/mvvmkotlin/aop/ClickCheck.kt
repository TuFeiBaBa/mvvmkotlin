package com.tufei.mvvmkotlin.aop

import android.support.annotation.StringRes

/**
 * @param tipRes  字符串资源的引用。多次点击时，弹吐司的提示。为0时，则不弹吐司。
 * @param interval 定义点击事件为重复点击的间隔。单位毫秒。
 * @param tag  因为CheckAspect只有一个实例。如果一个类里面有多个控件的使用该注解，
 * 点击控件1后，迅速点击控件2，如果没有tag加以区分，控件2会变成重复点击。
 *
 * @author tufei
 * @date 2018/3/4.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention
annotation class ClickCheck(@StringRes val tipRes: Int = 0, val interval: Int = 1000, val tag: Int = 0)
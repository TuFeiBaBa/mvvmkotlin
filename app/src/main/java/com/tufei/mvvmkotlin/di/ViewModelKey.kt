package com.tufei.mvvmkotlin.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author tufei
 * @date 2018/2/26.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention
@MapKey
annotation class ViewModelKey(val modelClass: KClass<out ViewModel>)


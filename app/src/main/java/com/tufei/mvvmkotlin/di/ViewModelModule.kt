package com.tufei.mvvmkotlin.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tufei.mvvmkotlin.ViewModelFactory
import com.tufei.mvvmkotlin.splash.SplashViewModel
import com.tufei.mvvmkotlin.test.AdapterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author tufei
 * @date 2018/2/26.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdapterViewModel::class)
    abstract fun bindAdapterViewModel(adapterViewModel: AdapterViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
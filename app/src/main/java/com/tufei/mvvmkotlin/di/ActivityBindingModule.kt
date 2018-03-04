package com.tufei.mvvmkotlin.di

import com.tufei.mvvmkotlin.splash.SplashActivity
import com.tufei.mvvmkotlin.splash.SplashModule
import com.tufei.mvvmkotlin.test.adapter.TestAdapterActivity
import com.tufei.mvvmkotlin.test.aop.ClickCheckActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author tufei
 * @date 2018/2/21.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract fun testAdapterActivity(): TestAdapterActivity

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract fun clickCheckActivity(): ClickCheckActivity
}
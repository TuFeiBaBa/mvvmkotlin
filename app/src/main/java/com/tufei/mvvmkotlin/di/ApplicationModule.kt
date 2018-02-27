package com.tufei.mvvmkotlin.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * @author tufei
 * @date 2018/2/21.
 */
@Module(includes = [ViewModelModule::class])
abstract class ApplicationModule {
    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context
}
package com.tufei.mvvmkotlin.db

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author tufei
 * @date 2018/3/10.
 */
@Module
class DaoModule {
    @Singleton
    @Provides
    fun providePersonsDao(context: Context) = PersonDatabase.getInstance(context).personDao()
}
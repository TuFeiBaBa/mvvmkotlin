package com.tufei.mvvmkotlin.db

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * @author tufei
 * @date 2018/3/10.
 */
@Module(includes = [DaoModule::class])
abstract class DbModule {

    @Singleton
    @Binds
    abstract fun bindPersonsLocalDataSource(personsLocalDataSource: PersonsLocalDataSource): PersonsDataSource
}
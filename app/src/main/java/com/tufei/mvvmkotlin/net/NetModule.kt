package com.tufei.mvvmkotlin.net

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author tufei
 * @date 2018/3/9.
 */
@Module
class NetModule {

    @Provides
    @Singleton
    internal fun provideHttpService(retrofit: Retrofit): HttpService {
        return retrofit.create(HttpService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return RetrofitFactory.createRetrofit()
    }
}
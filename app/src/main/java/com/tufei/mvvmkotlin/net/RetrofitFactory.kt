package com.tufei.mvvmkotlin.net

import com.google.gson.Gson
import com.tufei.mvvmkotlin.constant.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author tufei
 * @date 2018/3/9.
 */
object RetrofitFactory {

    init {
        createRetrofit()
    }

    lateinit var retrofit: Retrofit

    private fun createRetrofit() {
        val gsonConverterFactory = GsonConverterFactory.create(Gson())
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json; charset=UTF-8")
                            .build()
                    chain.proceed(request)
                }
                .connectTimeout(Constant.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constant.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
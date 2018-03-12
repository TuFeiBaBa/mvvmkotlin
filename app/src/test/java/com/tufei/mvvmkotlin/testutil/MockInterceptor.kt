package com.tufei.mvvmkotlin.testutil

import okhttp3.*
import okio.Okio
import java.io.IOException
import java.nio.charset.StandardCharsets


/**
 * @author tufei
 * @date 2018/3/11.
 */
class MockInterceptor(private val fileName: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val inputStream = javaClass.classLoader.getResourceAsStream("json/" + fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        return Response.Builder()
                .code(200)
                .message(inputStream.toString())
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"),
                        source.readString(StandardCharsets.UTF_8).toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
    }
}
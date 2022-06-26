package com.example.westernsanctions.api


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class ApiKeyInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request = original.newBuilder()
            .header("X-RapidAPI-Key",RetrofitService.API_KEY)
            .addHeader("X-RapidAPI-Host",RetrofitService.API_HOST)
            .build()
    return chain.proceed(request)
    }

}
package com.example.westernsanctions.api

import com.example.westernsanctions.model.CompanySanctionsItem
import com.example.westernsanctions.model.PersonalSanctionsItem
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("?")
    suspend fun getPersonalSanctionList(@Query("search") name: String): Response<List<PersonalSanctionsItem>>

    @GET("?")
    suspend fun getCompanySanctionList(@Query("search") searchCompany: String): Response<List<CompanySanctionsItem>>

    companion object {
        const val BASE_URL = "https://sanctions-scanner.p.rapidapi.com/"
        const val API_KEY = "885cc08d5amshb470a167dafe69cp1c465fjsnbe7a5869239e"
        const val API_HOST = "sanctions-scanner.p.rapidapi.com"

        fun create(client: OkHttpClient): RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }

}
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
    suspend fun getCompanySanctionList(@Query("search") searchCompany: String): Response<CompanySanctionsItem>

    companion object {
        const val BASE_URL = "https://sanctions-scanner.p.rapidapi.com/"
        const val API_KEY = "6443e69d12msh83f17f7e2c4f099p1b8a92jsne890324bf419"
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
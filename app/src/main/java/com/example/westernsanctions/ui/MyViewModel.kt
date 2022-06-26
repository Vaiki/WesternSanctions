package com.example.westernsanctions.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westernsanctions.api.ApiKeyInterceptor
import com.example.westernsanctions.api.RetrofitService
import com.example.westernsanctions.model.PersonalSanctionsItem
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MyViewModel : ViewModel() {
    val personalSanctionsLiveData = MutableLiveData<List<PersonalSanctionsItem>>()

    fun getResultPersonalSanctions() {
        viewModelScope.launch {
            val response = RetrofitService.create(interceptor()).getPersonalSanctionList("putin")
            if (response.isSuccessful) {
                personalSanctionsLiveData.postValue(response.body())
            }
        }
    }
    private fun interceptor(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }
}
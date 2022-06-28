package com.example.westernsanctions.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westernsanctions.api.ApiKeyInterceptor
import com.example.westernsanctions.api.RetrofitService
import com.example.westernsanctions.model.CompanySanctionsItem
import com.example.westernsanctions.model.PersonalSanctionsItem
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MyViewModel : ViewModel() {
    val personalSanctionsLiveData = MutableLiveData<List<PersonalSanctionsItem>>()
    val companySanctionsLiveData = MutableLiveData<List<CompanySanctionsItem>>()

    fun getResultPersonalSanctions(person:String) {
        viewModelScope.launch {
            val response = RetrofitService.create(interceptor()).getPersonalSanctionList(person)
            if (response.isSuccessful) {
                personalSanctionsLiveData.postValue(response.body())
            }
        }
    }
    fun getResultCompanySanctions(nameCompany:String) {
        viewModelScope.launch {
            val response = RetrofitService.create(interceptor()).getCompanySanctionList(nameCompany)
            if (response.isSuccessful) {
                companySanctionsLiveData.postValue(response.body())
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
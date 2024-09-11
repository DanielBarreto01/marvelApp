package com.example.marvelapp.data.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private var apiService: ApiService? = null
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    fun getInstance(): ApiService =
        apiService ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}

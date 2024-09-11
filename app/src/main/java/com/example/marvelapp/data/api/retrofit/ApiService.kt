package com.example.marvelapp.data.api.retrofit
import com.example.marvelapp.data.api.models.BaseResponse
import com.example.marvelapp.data.api.models.Characters
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): BaseResponse
}

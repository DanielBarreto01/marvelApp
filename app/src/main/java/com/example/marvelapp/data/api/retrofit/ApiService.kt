package com.example.marvelapp.data.api.retrofit
import com.example.marvelapp.data.api.models.characters.BaseResponse
import com.example.marvelapp.data.api.models.comics.ComicsResponse
import com.example.marvelapp.data.api.models.series.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
    ): BaseResponse

    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): BaseResponse

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): ComicsResponse

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): SeriesResponse
}




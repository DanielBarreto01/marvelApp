package com.example.marvelapp.data.api.models.characters

data class BaseResponse(
    val code: Int,
    val status: String,
    val data: DataContainer
)

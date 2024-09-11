package com.example.marvelapp.data.api.models

data class BaseResponse(
    val code: Int,
    val status: String,
    val data: DataContainer
)

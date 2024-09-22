package com.example.marvelapp.data.api.models.characters

data class Characters(
    val info: BaseResponse,
    val results: List<Character>
)

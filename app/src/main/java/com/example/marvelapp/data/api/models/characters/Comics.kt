package com.example.marvelapp.data.api.models.characters


data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicItem>,
    val returned: Int
)
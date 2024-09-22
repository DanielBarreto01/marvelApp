package com.example.marvelapp.data.api.models.characters

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<SeriesItem>,
    val returned: Int
)

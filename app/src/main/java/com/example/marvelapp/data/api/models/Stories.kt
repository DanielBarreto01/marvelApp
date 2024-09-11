package com.example.marvelapp.data.api.models

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<StoryItem>,
    val returned: Int
)

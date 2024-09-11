package com.example.marvelapp.data.api.models

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<EventItem>,
    val returned: Int
)

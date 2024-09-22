package com.example.marvelapp.data.api.models.characters

data class ResourceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ResourceItem>,
    val returned: Int
)
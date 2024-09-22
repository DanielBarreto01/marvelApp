package com.example.marvelapp.data.api.models.comics

data class ComicsResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: ComicsData
)

data class ComicsData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
)

data class Comic(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Int,
    val variantDescription: String,
    val description: String?,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val textObjects: List<TextObject>,
    val resourceURI: String,
    val urls: List<Url>,
    val series: Series,
    val variants: List<Variant>,
    val collections: List<Collection>,
    val collectedIssues: List<CollectedIssue>,
    val dates: List<Date>,
    val prices: List<Price>,
    val thumbnail: Thumbnail,
    val images: List<Image>,
    val creators: Creators,
    val characters: Characters,
    val stories: Stories,
    val events: Events
)

data class TextObject(
    val type: String,
    val language: String,
    val text: String
)

data class Url(
    val type: String,
    val url: String
)

data class Series(
    val resourceURI: String,
    val name: String
)

data class Variant(
    val resourceURI: String,
    val name: String
)

data class Collection(
    val resourceURI: String,
    val name: String
)

data class CollectedIssue(
    val resourceURI: String,
    val name: String
)

data class Date(
    val type: String,
    val date: String
)

data class Price(
    val type: String,
    val price: Float
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Image(
    val path: String,
    val extension: String
)

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<CreatorItem>,
    val returned: Int
)

data class CreatorItem(
    val resourceURI: String,
    val name: String,
    val role: String
)

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<CharacterItem>,
    val returned: Int
)

data class CharacterItem(
    val resourceURI: String,
    val name: String
)

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<StoryItem>,
    val returned: Int
)

data class StoryItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<EventItem>,
    val returned: Int
)

data class EventItem(
    val resourceURI: String,
    val name: String
)
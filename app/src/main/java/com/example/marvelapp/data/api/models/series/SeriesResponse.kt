package com.example.marvelapp.data.api.models.series

data class SeriesResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Serie>
)

data class Serie(
    val id: Int,
    val title: String,
    val description: String?,
    val resourceURI: String,
    val urls: List<Url>,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val type: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val creators: Creators,
    val characters: Characters,
    val stories: Stories,
    val comics: Comics,
    val events: Events,
    val next: Any?,
    val previous: Any?
)

data class Url(
    val type: String,
    val url: String
)

data class Thumbnail(
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

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicItem>,
    val returned: Int
)

data class ComicItem(
    val resourceURI: String,
    val name: String
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
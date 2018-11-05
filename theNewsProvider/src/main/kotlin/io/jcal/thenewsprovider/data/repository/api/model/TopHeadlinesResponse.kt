package io.jcal.thenewsprovider.data.repository.api.model

import com.squareup.moshi.Json

data class TopHeadlinesResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)
data class Source(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)

data class Article(
    @Json(name = "author")
    val author: Any,
    @Json(name = "content")
    val content: Any,
    @Json(name = "description")
    val description: Any,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "source")
    val source: Source,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: Any
)
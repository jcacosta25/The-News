package io.jcal.thenewsprovider.repository.api.model

import com.squareup.moshi.Json
import java.util.*

data class ArticlesResponse(
    @Json(name = "articles")
    val articles: List<ArticleResponse>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)

data class SourcesResponse(
    @Json(name = "status")
    val status: String,
    @Json(name = "sources")
    val sources: List<SourceResponse>
)

data class SourceResponse(
    @Json(name = "category")
    val category: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

data class ArticleResponse(
    @Json(name = "author")
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: Date,
    @Json(name = "source")
    val source: SourceResponse,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?
)
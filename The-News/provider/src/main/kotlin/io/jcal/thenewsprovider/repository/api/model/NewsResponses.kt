package io.jcal.thenewsprovider.repository.api.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("articles")
    val articles: List<ArticleResponse>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

data class SourcesResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("sources")
    val sources: List<SourceResponse>
)

data class SourceResponse(
    @SerializedName("category")
    val category: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class ArticleResponse(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: SourceResponse,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)
package io.jcal.thenewsprovider.repository.mapper.model

data class ArticleModel(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val date: String = "",
    val sourceName: String = "",
    val sourceId: String = "",
    val url: String = "",
    val image: String = "",
    val title: String = ""
) : BaseModel()

data class SourceModel(
    val id: String = "",
    val category: String = "",
    val country: String = "",
    val description: String = "",
    val language: String = "",
    val name: String = "",
    val url: String = ""
) : BaseModel()

data class ListArticlesModel(
    val articles: MutableList<ArticleModel> = mutableListOf()
) : BaseModel()

data class ListSourcesModel(val sources: List<SourceModel> = listOf()) : BaseModel()
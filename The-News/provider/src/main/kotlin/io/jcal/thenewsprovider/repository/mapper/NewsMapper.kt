package io.jcal.thenewsprovider.repository.mapper

import io.jcal.thenewsprovider.repository.api.model.ArticleResponse
import io.jcal.thenewsprovider.repository.api.model.ArticlesResponse
import io.jcal.thenewsprovider.repository.db.entity.ArticleEntity
import io.jcal.thenewsprovider.repository.mapper.model.ArticleModel
import io.jcal.thenewsprovider.repository.mapper.model.BaseModel
import io.jcal.thenewsprovider.repository.mapper.model.BaseModel.Companion.BASE_ERROR_CODE
import io.jcal.thenewsprovider.repository.mapper.model.BaseModel.Companion.LOADING
import io.jcal.thenewsprovider.repository.mapper.model.BaseModel.Companion.SUCCESS
import io.jcal.thenewsprovider.repository.mapper.model.ListArticlesModel
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun convert(response: ArticleResponse): ArticleModel {
        val model = ArticleModel(
            author = nullToString(response.author),
            content = nullToString(response.author),
            description = nullToString(response.author),
            date = nullToString(response.publishedAt),
            sourceId = nullToString(response.source.id),
            sourceName = nullToString(response.source.name),
            title = nullToString(response.title),
            url = nullToString(response.url),
            image = nullToString(response.urlToImage)
        )
        model.status = SUCCESS
        return model
    }

    fun convert(entity: ArticleEntity): ArticleModel {
        val model = ArticleModel(
            author = entity.author,
            content = entity.content,
            description = entity.description,
            date = entity.date,
            sourceId = entity.sourceId,
            sourceName = entity.sourceName,
            title = entity.title,
            url = entity.url,
            image = entity.imageUrl
        )
        model.status = SUCCESS
        return model
    }

    fun convert(model: ArticleModel): ArticleEntity =
        ArticleEntity(
            author = model.author,
            content = model.content,
            description = model.description,
            date = model.date,
            sourceId = model.sourceId,
            sourceName = model.sourceName,
            title = model.title,
            imageUrl = model.image,
            url = model.url
        )


    fun <T : BaseModel> createDomainModel(
        errorCode: Int = 0,
        clazz: Class<T>,
        status: String = LOADING
    ): T {
        val model = clazz.newInstance()
        if (errorCode != BASE_ERROR_CODE) {
            model.setError(errorCode)
        } else {
            model.status = status
        }
        return model
    }

    fun convert(entities: List<ArticleEntity>): ListArticlesModel = ListArticlesModel(
        articles = entities.map { convert(it) } as MutableList<ArticleModel>
    )

    fun convert(response: ArticlesResponse): ListArticlesModel =
        ListArticlesModel(
            articles = response.articles.map { convert(it) } as MutableList<ArticleModel>
        )

    private fun nullToString(string: String?): String = string ?: ""

}
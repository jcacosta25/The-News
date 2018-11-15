package io.jcal.thenews.ui.mapper

import io.jcal.thenews.ui.model.ArticleUiModel
import io.jcal.thenews.ui.model.ListArticlesUiModel
import io.jcal.thenewsprovider.repository.mapper.model.ArticleModel
import io.jcal.thenewsprovider.repository.mapper.model.ListArticlesModel
import javax.inject.Inject

class UiMapper @Inject constructor() {

    private fun convert(model: ArticleModel): ArticleUiModel {
        val uiModel = ArticleUiModel(
            author = model.author,
            content = model.content,
            description = model.description,
            date = model.date,
            sourceName = model.sourceName,
            sourceId = model.sourceId,
            url = model.url,
            image = model.image,
            title = model.title
        )
        uiModel.status = model.status
        return uiModel
    }

    fun convert(models: ListArticlesModel): ListArticlesUiModel {
        val uiModel = ListArticlesUiModel(
            articles = models.articles.map { convert(it) } as MutableList<ArticleUiModel>
        )
        uiModel.status = models.status
        return uiModel
    }

}
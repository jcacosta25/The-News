package io.jcal.thenews.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleUiModel(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val date: String = "",
    val sourceName: String = "",
    val sourceId: String = "",
    val url: String = "",
    val image: String = "",
    val title: String = ""
) : BaseUiModel(), Parcelable

@Parcelize
data class SourceUiModel(
    val id: String = "",
    val category: String = "",
    val country: String = "",
    val description: String = "",
    val language: String = "",
    val name: String = "",
    val url: String = ""
) : BaseUiModel(), Parcelable

@Parcelize
data class ListArticlesUiModel(
    val articles: MutableList<ArticleUiModel> = mutableListOf()
) : BaseUiModel(), Parcelable

@Parcelize
open class BaseUiModel(
    var status: String = LOADING,
    private var error: Boolean = false,
    private var errorCode: Int = BASE_ERROR_CODE
) : Parcelable {
    fun setError(errorCode: Int) {
        this.errorCode = errorCode
        this.error = true
        this.status = ERROR
    }

    companion object {
        const val SUCCESS = "success"
        const val LOADING = "loading"
        const val ERROR = "error"
        const val BASE_ERROR_CODE = 0
    }
}
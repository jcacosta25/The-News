package io.jcal.thenewsprovider.repository.mapper.model

open class BaseModel(
    var status: String = LOADING,
    var error: Boolean = false,
    var errorCode: Int = BASE_ERROR_CODE
) {
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
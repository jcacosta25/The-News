package io.jcal.thenewsprovider.repository.api.factory


import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) return null
        val observable: Type = getParameterUpperBound(UPPER_BOUND_PARAMETER, (returnType as ParameterizedType))
        val rawObservable = getRawType(observable)
        if (rawObservable != ApiResponse::class.java) throw IllegalArgumentException("type must be a resource")
        if ((observable is ParameterizedType).not()) throw IllegalArgumentException("resource must be parameterized")
        val bodyType = getParameterUpperBound(UPPER_BOUND_PARAMETER, (observable as ParameterizedType))
        return LiveDataCallAdapter<Any>(bodyType)
    }

    companion object {
        fun create(): LiveDataCallAdapterFactory = LiveDataCallAdapterFactory()

        private const val UPPER_BOUND_PARAMETER = 0
    }
}

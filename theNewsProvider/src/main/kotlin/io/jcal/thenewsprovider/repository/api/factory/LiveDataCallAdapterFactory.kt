package io.jcal.thenewsprovider.repository.api.factory

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class LiveDataCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) return null

        if (returnType !is ParameterizedType) throw IllegalStateException("LiveData return type must be parameterized  as LiveData<Foo> or LiveData<? extends Foo>")

        val innerType = CallAdapter.Factory.getParameterUpperBound(0, returnType)

        // Generic type is not Response<T>. Use it for body-only adapter.
        if (CallAdapter.Factory.getRawType(innerType) != Response::class.java) return BodyCallAdapter<Any>(
            innerType
        )


        if (innerType !is ParameterizedType) throw IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>")

        val responseType = getParameterUpperBound(0, innerType)
        return ResponseCallAdapter<Any>(
            responseType
        )
    }

    class BodyCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<R>> {

        override fun responseType(): Type = responseType

        override fun adapt(call: Call<R>): LiveData<R> = object : LiveData<R>() {
            init {
                call.enqueue(object : Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        if (response.isSuccessful) {
                            postValue(response.body())
                        } else {
                            postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<R>, throwable: Throwable) {
                        postValue(null)
                    }
                })
            }

            override fun onInactive() {
                call.cancel()
            }
        }

    }

    class ResponseCallAdapter<R>(private val responseType: Type) :
        CallAdapter<R, LiveData<ApiResponse<R>>> {

        override fun responseType(): Type = responseType

        override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
            return object : LiveData<ApiResponse<R>>() {
                init {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            value = ApiResponse(
                                response
                            )
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            value = ApiResponse(
                                throwable
                            )
                        }
                    })
                }

                override fun onInactive() {
                    super.onInactive()
                    call.cancel()
                }
            }
        }
    }


    companion object {
        fun create(): LiveDataCallAdapterFactory =
            LiveDataCallAdapterFactory()
    }
}
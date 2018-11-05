package io.jcal.thenewsprovider.data.repository.api.factory

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.collection.ArrayMap
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

/**
 * Common class used by API responses.
 *
 * @param <T>
</T> */
class ApiResponse<T> {
    val code: Int
    @Nullable
    val body: T?
    @Nullable
    val errorMessage: String?
    @NonNull
    val links: MutableMap<String, String>

    val isSuccessful: Boolean
        get() = code in SUCCESS..REDIRECTION.dec()

    val nextPage: Int?
        get() {
            val next = links[NEXT_LINK] ?: return null
            val matcher = PAGE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() !== GROUP_ONE) {
                return null
            }
            return try {
                Integer.parseInt(matcher.group(GROUP_ONE))
            } catch (ex: NumberFormatException) {
                Timber.e("NumberFormatException cannot parse next page from $next")
                null
            }

        }

    constructor(error: Throwable) {
        code = INVALID_SERVER_ERROR
        body = null
        errorMessage = error.message
        links = Collections.emptyMap()
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().toString()
                } catch (ignored: IOException) {
                    Timber.e("IOException error while parsing response")
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
        val linkHeader = response.headers().get(LINK)
        if (linkHeader == null) {
            links = Collections.emptyMap()
        } else {
            links = ArrayMap()
            val matcher = LINK_PATTERN.matcher(linkHeader)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == GROUP_TWO) {
                    links[matcher.group(GROUP_TWO)] = matcher.group(GROUP_ONE)
                }
            }
        }
    }

    companion object {
        private val LINK_PATTERN = Pattern
            .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private const val NEXT_LINK = "next"
        private const val LINK = "link"

        private const val GROUP_ONE = 1
        private const val GROUP_TWO = 2
        const val SUCCESS = 200
        const val SUCCESS_NO_CONTENT = 204
        const val REDIRECTION = 300
        const val BAD_REQUEST_ERROR = 400
        const val UNAUTHORIZED_ERROR = 401
        const val FORBIDDEN_ERROR = 403
        const val NOT_FOUND_ERROR = 404
        const val INVALID_SERVER_ERROR = 500
        const val NO_NETWORK_RESPONSE_CODE = -1
    }
}


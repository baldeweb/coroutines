package com.example.coroutines.data

import retrofit2.Response
import java.io.IOException

open class ServiceManager {
    companion object {
        private const val HTTP_OK = 200
        private const val HTTP_UNAUTHORIZED = 401
        private const val HTTP_INTERNAL_SERVER_ERROR = 500

        fun <T> serviceCallerr(
                api: Response<T>,
                onSuccess: (T) -> Unit,
                onError: (Any) -> Unit
        ) {
            if (api.code() == HTTP_OK) {
                try {
                    api.body()?.run { onSuccess.invoke(this) }
                } catch (exception: IOException) {
                    errorResponse(api, onError)
                }
            } else {
                errorResponse(api, onError)
            }
        }

        private fun errorResponse(
            api: Response<*>,
            onError: (Any) -> Unit
        ) {
            try {
                applyThrowableResponse(api, onError)
            } catch (exception: IOException) {
                onError.invoke(Any())
            }
        }

        @Synchronized
        private fun applyThrowableResponse(
                api: Response<*>,
                onError: (Any) -> Unit
        ) {
            /*val error = Any()
            error.httpCode = api.code()
            var errorSerialized = (api.errorBody()?.bytes() as ByteArray).toString()
            error.throwable = Throwable(api.errorBody().toString())
            when (error.httpCode) {
                HTTP_UNAUTHORIZED -> onError.invoke(error)
                in HTTP_INTERNAL_SERVER_ERROR..599 -> onError.invoke(error)
                else -> onError.invoke(ServiceErrorModel(0))
            }*/
            when (api.code()) {
                HTTP_UNAUTHORIZED -> onError.invoke(Any())
                in HTTP_INTERNAL_SERVER_ERROR..599 -> onError.invoke(Any())
                else -> onError.invoke(Any())
            }
        }
    }
}
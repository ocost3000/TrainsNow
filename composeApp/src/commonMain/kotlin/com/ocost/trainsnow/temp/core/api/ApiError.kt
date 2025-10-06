package com.ocost.trainsnow.temp.core.api

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Represents various failure scenarios that can occur during an API request.
 */
sealed class ApiError {
    /**
     * HTTP 4xx errors - Client-side errors (Bad Request, Unauthorized, Forbidden, Not Found, etc.)
     *
     * @property statusCode The HTTP status code (400-499)
     * @property message Error message from the server or exception
     * @property body Optional response body containing error details
     */
    data class HttpClient(
        val statusCode: Int,
        val message: String,
        val body: String? = null
    ) : ApiError()

    /**
     * HTTP 5xx errors - Server-side errors (Internal Server Error, Bad Gateway, Service Unavailable, etc.)
     *
     * @property statusCode The HTTP status code (500-599)
     * @property message Error message from the server or exception
     * @property body Optional response body containing error details
     */
    data class HttpServer(
        val statusCode: Int,
        val message: String,
        val body: String? = null
    ) : ApiError()

    /**
     * HTTP 3xx redirect responses that weren't automatically followed.
     *
     * @property statusCode The HTTP status code (300-399)
     * @property message Error message describing the redirect
     * @property location The redirect location if available
     */
    data class Redirect(
        val statusCode: Int,
        val message: String,
        val location: String? = null
    ) : ApiError()

    /**
     * Network connectivity issues (no internet, DNS resolution failure, connection timeout, etc.)
     *
     * @property message Human-readable error message
     * @property cause The underlying Throwable
     */
    data class NetworkApiError(
        val message: String,
        val cause: Throwable
    ) : ApiError()

    /**
     * JSON or other data serialization/deserialization errors.
     *
     * @property message Human-readable error message
     * @property cause The underlying SerializationException
     */
    data class Serialization(
        val message: String,
        val cause: SerializationException
    ) : ApiError()

    /**
     * Request or socket timeout errors.
     *
     * @property message Human-readable error message
     * @property timeoutType The type of timeout that occurred
     */
    data class TimeoutApiError(
        val message: String,
        val timeoutType: TimeoutType
    ) : ApiError() {
        enum class TimeoutType {
            CONNECTION,
            REQUEST,
            SOCKET
        }
    }

    /**
     * Unexpected errors that don't fall into other categories.
     *
     * @property message Human-readable error message
     * @property cause The underlying throwable
     */
    data class Unknown(
        val message: String,
        val cause: Throwable
    ) : ApiError()
}

/**
 * Converts the error back to a Throwable for re-throwing if needed.
 */
private fun ApiError.toThrowable(): Throwable = when (this) {
    is ApiError.HttpClient -> IllegalStateException("HTTP $statusCode: $message")
    is ApiError.HttpServer -> IllegalStateException("HTTP $statusCode: $message")
    is ApiError.Redirect -> IllegalStateException("HTTP $statusCode Redirect: $message")
    is ApiError.NetworkApiError -> cause
    is ApiError.Serialization -> cause
    is ApiError.TimeoutApiError -> IllegalStateException(message)
    is ApiError.Unknown -> cause
}

suspend fun exceptionToApiError(e: Throwable): ApiError =
    when (e) {
        is CancellationException -> throw e
        is ClientRequestException -> ApiError.HttpClient(
            statusCode = e.response.status.value,
            message = e.message,
            body = e.response.bodyAsText()
        )
        is ServerResponseException -> ApiError.HttpServer(
            statusCode = e.response.status.value,
            message = e.message,
            body = e.response.bodyAsText()
        )
        is RedirectResponseException -> ApiError.Redirect(
            statusCode = e.response.status.value,
            message = e.message,
            location = e.response.headers["Location"]
        )
        is SerializationException -> ApiError.Serialization(
            message = "Failed to parse server response: ${e.message}",
            cause = e
        )
        else -> ApiError.Unknown(
            message = "Unexpected error: ${e.message}",
            cause = e
        )
    }

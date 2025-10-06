package com.ocost.trainsnow.temp.core.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Creates a configured HttpClient for the Transiter API.
 *
 * @param enableLogging Whether to enable request/response logging
 * @param json Custom JSON configuration (optional)
 * @return Configured HttpClient instance
 */
fun createTransiterHttpClient(
    enableLogging: Boolean = false,
    json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = false
        encodeDefaults = true
    }
): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = if (enableLogging) LogLevel.HEADERS else LogLevel.NONE
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
    }
}

/**
 * Creates a TransiterApi instance with a default HttpClient configuration.
 *
 * @param baseUrl The base URL of the Transiter API instance
 * @param enableLogging Whether to enable request/response logging
 * @return Configured TransiterApi instance
 */
fun createTransiterApi(
    baseUrl: String = "https://demo.transiter.dev",
    enableLogging: Boolean = false
): TransiterApi {
    val client = createTransiterHttpClient(enableLogging)
    return TransiterApi(client, baseUrl)
}

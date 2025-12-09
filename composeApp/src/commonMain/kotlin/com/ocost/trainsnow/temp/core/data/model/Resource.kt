package com.ocost.trainsnow.temp.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Resource(
    val path: String,
    val url: String,
)

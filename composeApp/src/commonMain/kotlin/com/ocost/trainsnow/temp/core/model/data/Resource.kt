package com.quicktrain.core.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Resource(
    val path: String,
    val url: String,
)

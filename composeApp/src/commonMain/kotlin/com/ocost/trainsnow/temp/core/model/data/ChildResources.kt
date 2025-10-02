package com.quicktrain.core.model.data

import kotlinx.serialization.Serializable

@Serializable
data class ChildResources(
    val count: Long,
    val path: String,
    val url: String,
)

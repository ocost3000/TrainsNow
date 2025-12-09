package com.ocost.trainsnow.temp.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChildResources(
    val count: Long,
    val path: String,
    val url: String,
)

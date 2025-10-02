package com.quicktrain.core.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feed(
    val id: String,
    val resource: Resource,
    val system: System.Reference,
    @SerialName("last_update_ms") val lastUpdateMs: Long?,
    @SerialName("last_successful_update_ms") val lastSuccessfulUpdateMs: Long,
    @SerialName("last_skipped_update_ms") val lastSkippedUpdateMs: Long,
    @SerialName("last_failed_update_ms") val lastFailedUpdateMs: Long,
) {
    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
        val system: System.Reference,
    )
}

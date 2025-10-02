package com.quicktrain.core.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val id: String,
    val resource: Resource,
    val route: Route.Reference,
    @SerialName("started_at") val startedAt: Long,
    val vehicle: Vehicle.Reference,
    @SerialName("direction_id") val directionId: Boolean,
    @SerialName("stop_times") val stopTimes: List<StopTime>,
    val shape: Shape.Reference,
    val alerts: Alert.Reference,
) {

    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
        val route: Route.Reference,
        val destination: Stop.Reference?, // Nullable as it's only populated in some contexts and deprecated
        val vehicle: Vehicle.Reference,
        @SerialName("direction_id") val directionId: Boolean? // Nullable if it might not always be present in a reference
    )
}

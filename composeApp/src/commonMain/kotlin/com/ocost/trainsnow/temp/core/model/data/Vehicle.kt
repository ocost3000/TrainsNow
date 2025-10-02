package com.quicktrain.core.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
    val id: Int,
    val trip: Trip.Reference,
    val latitude: Double,
    val longitude: Double,
    val bearing: Float,
    val odometer: Double,
    val speed: Float,
    @SerialName("stop_sequence") val stopSequence: Int,
    val stop: Stop.Reference,
    @SerialName("current_status") val currentStatus: CurrentStatus,
    @SerialName("updated_at") val updatedAt: Long,
    @SerialName("congestion_level") val congestionLevel: CongestionLevel,
    @SerialName("occupancy_status") val occupancyStatus: OccupancyStatus,
    @SerialName("occupancy_percentage") val occupancyPercentage: Int,
) {
    @Serializable
    enum class CongestionLevel(val value: Int) {
        UNKNOWN_CONGESTION_LEVEL(0),
        RUNNING_SMOOTHLY(1),
        STOP_AND_GO(2),
        CONGESTION(3),
        SEVERE_CONGESTION(4);
    }

    @Serializable
    enum class CurrentStatus(val value: Int) {
        INCOMING_AT(0),
        STOPPED_AT(1),
        IN_TRANSIT_TO(2),
    }

    @Serializable
    enum class OccupancyStatus(val value: Int) {
        EMPTY(0),
        MANY_SEATS_AVAILABLE(1),
        FEW_SEATS_AVAILABLE(2),
        STANDING_ROOM_ONLY(3),
        CRUSHED_STANDING_ROOM_ONLY(4),
        FULL(5),
        NOT_ACCEPTING_PASSENGERS(6)
    }

    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
    )
}

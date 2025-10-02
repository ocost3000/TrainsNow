package com.quicktrain.core.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StopTime(
    val stop: Stop.Reference,
    val trip: Trip.Reference,
    val destination: Stop.Reference,
    val vehicle: Vehicle.Reference,
    val arrival: EstimatedTime,
    val departure: EstimatedTime,
    val future: Boolean,
    @SerialName("stop_sequence") val stopSequence: Int,
    val headsign: String?,
    val track: String?,
) {
    @Serializable
    data class EstimatedTime(
        val time: Long,
        val delay: Long,
        val uncertainty: Long,
    )
}

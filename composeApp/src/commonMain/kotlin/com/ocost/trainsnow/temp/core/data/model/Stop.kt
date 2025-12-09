package com.ocost.trainsnow.temp.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stop(
    val id: String,
    val resource: Resource,
    val system: System.Reference?,
    val code: String?,
    val name: String,
    val description: String,
    @SerialName("zone_id") val zoneId: String?,
    val latitude: Double?,
    val longitude: Double?,
    val url: String?,
    val type: Type?,
    @SerialName("parent_stop") val parentStop: Reference?, // may not be same obj
    @SerialName("child_stops") val childStops: List<Reference>?, // may not be same obj
    val timezone: String?,
    @SerialName("wheelchair_boarding") val wheelchairBoarding: Boolean?,
    @SerialName("platform_code") val platformCode: String?,
    @SerialName("service_maps") val serviceMaps: List<ServiceMap>?,
    val alerts: List<Alert>?,
    @SerialName("stop_times") val stopTimes: StopTime?,
    val transfers: List<Transfer>?,
    @SerialName("headsign_rules") val headsignRules: List<HeadsignRule>?,
) {
    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
        val system: System.Reference?,
        val name: String,
    )

    @Serializable
    data class HeadsignRule(
        val stop: Reference,
        val priority: Int,
        val track: String, // nyc track
        val headsign: String,
    )

    @Serializable
    data class ServiceMap(
        @SerialName("config_id") val configId: String,
        val routes: Route.Reference,
    )

    @Serializable
    enum class Type(val value: Int) {
        STOP(0),
        STATION(1),
        ENTRANCE_OR_EXIT(2),
        GENERIC_NODE(3),
        BOARDING_AREA(4),
        PLATFORM(5)
    }

}

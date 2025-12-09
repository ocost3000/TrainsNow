package com.ocost.trainsnow.temp.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val id: String,
    val resource: Resource,
    val system: System.Reference,
    @SerialName("short_name") val shortName: String,
    @SerialName("long_name") val longName: String,
    val color: String,
    @SerialName("text_color") val textColor: String,
    val description: String,
    val url: String,
    @SerialName("sort_order") val sortOrder: Int,
    @SerialName("continuous_pickup") val continuousPickup: ContinuousPolicy,
    @SerialName("continuous_dropoff") val continuousDropOff: ContinuousPolicy,
    val type: Type,
    val agency: Agency,
    val alerts: Alert.Reference,
    @SerialName("estimated_headway") val estimatedHeadway: Int,
    @SerialName("service_maps") val serviceMaps: List<ServiceMap>,
) {

    @Serializable
    enum class ContinuousPolicy(val value: Int) {
        ALLOWED(0),
        NOT_ALLOWED(1),
        PHONE_AGENCY(2),
        COORDINATE_WITH_DRIVER(3)
    }

    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
        val system: System.Reference,
        val color: String,
    )

    @Serializable
    data class ServiceMap(
        @SerialName("config_id") val configId: String,
        val stops: List<Stop.Reference>,
    )

    @Serializable
    enum class Type(val value: Int) {
        LIGHT_RAIL(0),
        SUBWAY(1),
        RAIL(2),
        BUS(3),
        FERRY(4),
        CABLE_TRAM(5),
        AERIAL_LIFT(6),
        FUNICULAR(7),
        TROLLEY_BUS(11),
        MONORAIL(12),
        UNKNOWN(100)
    }
}

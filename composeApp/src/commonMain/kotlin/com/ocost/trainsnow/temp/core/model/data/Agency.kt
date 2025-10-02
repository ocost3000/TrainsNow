package com.quicktrain.core.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agency(
    val id: String,
    val resource: Resource,
    val system: System.Reference,
    val name: String,
    val url: String,
    val timezone: String,
    val language: String?, // language can sometimes be optional
    val phone: String?,    // phone can sometimes be optional
    @SerialName("fare_url") val fareUrl: String?, // fare_url can sometimes be optional
    val email: String?,   // email can sometimes be optional
    val routes: Route.Reference,
    val alerts: Alert.Reference,
) {
    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
        val system: System.Reference,
        val name: String,
    )
}

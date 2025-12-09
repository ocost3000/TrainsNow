package com.ocost.trainsnow.temp.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Alert(
    val id: String,
    val resource: Resource,
    val system: System.Reference,
    val cause: Cause,
    val effect: Effect,
    @SerialName("current_active_period") val currentActivePeriod: ActivePeriod?,
    @SerialName("all_active_periods") val allActivePeriods: List<ActivePeriod>,
    val header: Text,
    val description: List<Text>,
    val url: List<Text>
) {
    @Serializable
    data class ActivePeriod(
        @SerialName("starts_at") val startsAt: Long,
        @SerialName("ends_at") val endsAt: Long,
    )

    @Serializable
    enum class Cause(val value: Int) {
        UNKNOWN_CAUSE(0),

        // Gap for skipping 1
        OTHER_CAUSE(2),
        TECHNICAL_PROBLEM(3),
        STRIKE(4),
        DEMONSTRATION(5),
        ACCIDENT(6),
        HOLIDAY(7),
        WEATHER(8),
        MAINTENANCE(9),
        CONSTRUCTION(10),
        POLICE_ACTIVITY(11),
        MEDICAL_EMERGENCY(12),
    }

    @Serializable
    enum class Effect(val value: Int) {
        UNKNOWN_EFFECT(0),
        NO_SERVICE(1),
        REDUCED_SERVICE(2),
        SIGNIFICANT_DELAYS(3),
        DETOUR(4),
        ADDITIONAL_SERVICE(5),
        MODIFIED_SERVICE(6),
        OTHER_EFFECT(7),
        STOP_MOVED(9),
        NO_EFFECT(10),
        ACCESSIBILITY_ISSUE(11),
    }

    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
        val system: System.Reference,
        val cause: Cause,
        val effect: Effect,
    )

    @Serializable
    data class Text(
        val text: String,
        val language: String
    )
}

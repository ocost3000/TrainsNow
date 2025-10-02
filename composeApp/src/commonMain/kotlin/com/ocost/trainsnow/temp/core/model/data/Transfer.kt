package com.quicktrain.core.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Transfer(
    val id: String,
    val resource: Resource,
    val system: System.Reference,
    @SerialName("from_stop") val fromStop: Stop.Reference,
    @SerialName("to_stop") val toStop: Stop.Reference,
    val type: Type,
    @SerialName("min_transfer_time") val minTransferTime: Int
) {
    @Serializable
    enum class Type(val value: Int) {
        RECOMMENDED(0),
        TIMED(1),
        REQUIRES_TIME(2),
        NOT_POSSIBLE(3)
    }
}

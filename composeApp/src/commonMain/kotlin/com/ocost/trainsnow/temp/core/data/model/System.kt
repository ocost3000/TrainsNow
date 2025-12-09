package com.ocost.trainsnow.temp.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class System(
    val id: String,
    val resource: Resource,
    val name: String,
    val status: Status,
    val agencies: ChildResources,
    val feeds: ChildResources,
    val routes: ChildResources,
    val stops: ChildResources,
    val transfers: ChildResources
) {
    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource
    )

    @Serializable
    enum class Status(val value: Int) {
        UNKNOWN(0),
        INSTALLING(1),
        ACTIVE(2),
        INSTALL_FAILED(3),
        UPDATING(4),
        UPDATE_FAILED(5),
        DELETING(6)
    }
}

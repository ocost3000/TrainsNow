package com.quicktrain.core.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Shape(
    val id: String,
    val points: List<ShapePoint>,
) {
    @Serializable
    data class Reference(
        val id: String,
        val resource: Resource,
    )

    @Serializable
    data class ShapePoint(
        val latitude: Double,
        val longitude: Double,
        val distance: Double,
    )
}

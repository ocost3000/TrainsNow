package com.ocost.trainsnow.screens

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.ocost.trainsnow.components.StationList

data class StationPlatform(
    val directionTrue: String,
    val directionFalse: String,
    val stopName: String,
    val routes: List<Routes>
)

data class StationScreenState(
    val transfers: List<StationPlatform>

@Composable
fun StationScreen(

) {
    Surface {
        StationList()
    }
}
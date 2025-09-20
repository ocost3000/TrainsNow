package com.ocost.trainsnow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BusScreen(
    stack: List<String>,
    onPush: (String) -> Unit,
    onPop: () -> Unit,
    padding: PaddingValues,
    canPop: Boolean,
) {
    val tabTitle = "Bus"
    val routeTitle = stack.lastOrNull() ?: tabTitle

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("$tabTitle — $routeTitle")
        Button(onClick = { onPush("Detail ${stack.size}") }) {
            Text("Push detail screen")
        }
        Button(onClick = onPop, enabled = canPop) {
            Text("Pop")
        }
    }
}

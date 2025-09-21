package com.ocost.trainsnow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ocost.trainsnow.shared.icon.CircleIcon
import com.ocost.trainsnow.shared.icon.LineIconSpec

@Composable
fun SubwayScreen(
    stack: List<String>,
    onPush: (String) -> Unit,
    onPop: () -> Unit,
    padding: PaddingValues,
    canPop: Boolean,
) {
    val tabTitle = "Subway"
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

        // Demo: Programmatic line icons of varying sizes (performance-friendly)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CircleIcon(LineIconSpec("A", background = Color(0xFF0039A6)), size = 20.dp)
            CircleIcon(LineIconSpec("B", background = Color(0xFFFCCB00)), size = 28.dp)
            CircleIcon(LineIconSpec("Q", background = Color(0xFFFCCC0A)), size = 36.dp)
            CircleIcon(LineIconSpec("7", background = Color(0xFFB933AD)), size = 48.dp)
        }
        Text("CircleLetterIcon draws a circle + centered letter; scales cheaply without assets.")

        Button(onClick = { onPush("Detail ${stack.size}") }) {
            Text("Push detail screen")
        }
        Button(onClick = onPop, enabled = canPop) {
            Text("Pop")
        }
    }
}

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
import com.ocost.trainsnow.shared.icon.LineIcon
import com.ocost.trainsnow.shared.icon.LineIconShape
import com.ocost.trainsnow.shared.icon.LineIconSize

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
            LineIcon(
                character = "A",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(0xFF0039A6),
                foreground = Color.White,
                contentDescription = "A line"
            )
            LineIcon(
                character = "N",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(0xFFFCCB00),
                foreground = Color.White,
                contentDescription = "B line"
            )
            LineIcon(
                character = "Q",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(0xFFFCCC0A),
                foreground = Color.White,
                contentDescription = "Q line"
            )
            LineIcon(
                character = "7",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(0xFFB933AD),
                foreground = Color.White,
                contentDescription = "7 line"
            )
        }
        Text("LineIcon draws a circle + centered letter; scales cheaply without assets.")

        Button(onClick = { onPush("Detail ${stack.size}") }) {
            Text("Push detail screen")
        }
        Button(onClick = onPop, enabled = canPop) {
            Text("Pop")
        }
    }
}

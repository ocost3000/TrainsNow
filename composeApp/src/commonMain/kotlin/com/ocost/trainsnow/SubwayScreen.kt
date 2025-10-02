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
import com.ocost.trainsnow.shared.LineIcon
import com.ocost.trainsnow.shared.LineIconShape
import com.ocost.trainsnow.shared.LineIconSize

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
                background = Color(color = 0xFF0062CF),
                foreground = Color.White,
                contentDescription = "A line"
            )
            LineIcon(
                character = "N",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(color = 0xFFF6BC26),
                foreground = Color.Black,
                contentDescription = "B line"
            )
            LineIcon(
                character = "Q",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(color = 0xFFF6BC26),
                foreground = Color.Black,
                contentDescription = "Q line"
            )
            LineIcon(
                character = "7",
                size = LineIconSize.Large,
                shape = LineIconShape.Circle,
                background = Color(color = 0xFF9A38A1),
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

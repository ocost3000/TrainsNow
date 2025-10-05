package com.ocost.trainsnow.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.jetbrains.compose.ui.tooling.preview.Preview

private const val FontSizeRatio = 0.56f

enum class LineIconShape {
    Circle,
    Diamond,
    RoundedSquare
}

enum class LineIconSize(val dp: Dp) {
    Small(24.dp),
    Large(48.dp)
}

data class LineIconSpec(
    val letter: String,
    val background: Color,
    val foreground: Color = Color.White,
    val contentDescription: String? = null,
)

private object DiamondShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f
        val path = Path().apply {
            moveTo(cx, 0f)
            lineTo(w, cy)
            lineTo(cx, h)
            lineTo(0f, cy)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun LineIcon(
    character: String,
    size: LineIconSize,
    shape: LineIconShape,
    contentDescription: String,
    background: Color,
    foreground: Color = Color.White,
    typography: Typography = MaterialTheme.typography,
    modifier: Modifier = Modifier
) {
    val spec = LineIconSpec(
        letter = character,
        background = background,
        foreground = foreground,
        contentDescription = contentDescription,
    )
    val fontSize: TextUnit = remember(size) {
        (size.dp.value * FontSizeRatio).sp
    }

    MaterialTheme(typography = typography) {
        Box(
            modifier = modifier
                .size(size.dp)
                .background(
                    color = spec.background,
                    shape = when (shape) {
                        LineIconShape.Circle -> CircleShape
                        LineIconShape.Diamond -> DiamondShape
                        LineIconShape.RoundedSquare -> RoundedCornerShape(percent = 20)
                    }
                )
                .semantics { this.contentDescription = contentDescription },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = spec.letter,
                color = spec.foreground,
                style = MaterialTheme.typography.titleMedium,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun LineIconScreenPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    LineIcon(
                        character = "A",
                        shape = LineIconShape.Circle,
                        size = LineIconSize.Large,
                        background = Color(color = 0xFF0039A6),
                        foreground = Color.White,
                        contentDescription = "A line"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    LineIcon(
                        character = "B",
                        shape = LineIconShape.RoundedSquare,
                        size = LineIconSize.Large,
                        background = Color(color = 0xFF0099CC),
                        foreground = Color.White,
                        contentDescription = "B line"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    LineIcon(
                        character = "6",
                        shape = LineIconShape.Diamond,
                        size = LineIconSize.Large,
                        background = Color(color = 0xFF00933C),
                        foreground = Color.White,
                        contentDescription = "6 line"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    LineIcon(
                        character = "A",
                        shape = LineIconShape.Circle,
                        size = LineIconSize.Small,
                        background = Color(color = 0xFF0039A6),
                        foreground = Color.White,
                        contentDescription = "A line"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    LineIcon(
                        character = "B",
                        shape = LineIconShape.RoundedSquare,
                        size = LineIconSize.Small,
                        background = Color(color = 0xFF0099CC),
                        foreground = Color.White,
                        contentDescription = "B line"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    LineIcon(
                        character = "6",
                        shape = LineIconShape.Diamond,
                        size = LineIconSize.Small,
                        background = Color(color = 0xFF00933C),
                        foreground = Color.White,
                        contentDescription = "6 line"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CircleIcon_Preview() {
    MaterialTheme {
        LineIcon(
            character = "A",
            shape = LineIconShape.Circle,
            size = LineIconSize.Large,
            background = Color(color = 0xFF0039A6),
            foreground = Color.White,
            contentDescription = "A line"
        )
    }
}

@Preview
@Composable
private fun RoundedSquareIconPreview() {
    MaterialTheme {
        LineIcon(
            character = "B",
            shape = LineIconShape.RoundedSquare,
            size = LineIconSize.Large,
            background = Color(color = 0xFF0099CC),
            foreground = Color.White,
            contentDescription = "B line"
        )
    }
}

@Preview
@Composable
private fun DiamondIconPreview() {
    MaterialTheme {
        LineIcon(
            character = "F",
            shape = LineIconShape.Diamond,
            size = LineIconSize.Large,
            background = Color(color = 0xFFD52B1E),
            foreground = Color.White,
            contentDescription = "F line"
        )
    }
}

@Preview
@Composable
private fun CircleIconSmallPreview() {
    MaterialTheme {
        LineIcon(
            character = "A",
            shape = LineIconShape.Circle,
            size = LineIconSize.Small,
            background = Color(color = 0xFF0039A6),
            foreground = Color.White,
            contentDescription = "A line"
        )
    }
}

@Preview
@Composable
private fun RoundedSquareIconSmallPreview() {
    MaterialTheme {
        LineIcon(
            character = "B",
            shape = LineIconShape.RoundedSquare,
            size = LineIconSize.Small,
            background = Color(color = 0xFF0099CC),
            foreground = Color.White,
            contentDescription = "B line"
        )
    }
}

@Preview
@Composable
private fun DiamondIconSmallPreview() {
    MaterialTheme {
        LineIcon(
            character = "F",
            shape = LineIconShape.Diamond,
            size = LineIconSize.Small,
            background = Color(color = 0xFFD52B1E),
            foreground = Color.White,
            contentDescription = "F line"
        )
    }
}
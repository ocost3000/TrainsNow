package com.ocost.trainsnow.shared.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Two strategies to render a subway-style circular line icon with a single character in the middle.
 *
 * 1) SVG-derived icon (vector asset)
 *    - Implementation: Use [SvgLineIcon] with a [Painter] loaded from a vector/SVG asset.
 *    - Pros:
 *      - Pixel-perfect, brand-accurate when artwork is provided by the agency.
 *      - No runtime text layout required; the glyph is baked into the vector path.
 *      - Can reuse a single asset across screens; painters are cacheable via remember.
 *    - Cons (performance/operational):
 *      - Each distinct line/letter/color generally needs its own asset.
 *      - Vector parsing has an initial cost; complex paths can be expensive to rasterize, especially when scaled every frame or animated.
 *      - Text inside the SVG is typically converted to paths, increasing node count and memory.
 *      - Asset management multiplies for regions (MTA, MBTA, BART, etc.).
 *
 * 2) Programmatic composable (draw a circle; center a Text) – see [CircleIcon].
 *    - Implementation: Compose primitives; a circular background with a centered [Text].
 *    - Pros (performance/scalability):
 *      - No asset I/O or vector parsing; very cheap to compose.
 *      - Scales to arbitrary sizes deterministically by deriving [fontSize] from the icon diameter.
 *      - Easy theming and dynamic colors (accessibility, themes, night mode, contrast variants).
 *      - Minimal memory; relies on a single text layout and a simple shape clip.
 *    - Cons:
 *      - Typography metrics may not exactly match agency artwork (kerning/optical alignment).
 *      - Requires choosing a fallback font that resembles the brand when exact glyph outlines are mandated.
 *
 * Guidance:
 * - Prefer [CircleIcon] for dynamic lists/maps where you render many icons at various sizes, or when you need
 *   on-the-fly color/letter changes. It minimizes allocations and avoids asset churn.
 * - Use [SvgLineIcon] where brand fidelity is critical and official vectors must be shown exactly as provided.
 */

/** Data describing a subway-like line icon. */
data class LineIconSpec(
    val letter: String,
    val background: Color,
    val foreground: Color = Color.White,
    val contentDescription: String? = null,
)

/**
 * Programmatic, scalable line icon. Draws a perfect circle with [spec.background] and centers [spec.letter].
 *
 * Performance notes:
 * - Uses a simple shape background and one text layout.
 * - [fontSize] is derived from [size] to keep proportions consistent across densities and platforms.
 */
@Composable
internal fun CircleIcon(
    spec: LineIconSpec,
    size: Dp,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = remember(size) { (size.value * 0.56f).sp },
    fontWeight: FontWeight = FontWeight.Bold,
) {
    val cd = spec.contentDescription ?: "${spec.letter} line"
    Box(
        modifier = modifier
            .size(size)
            .background(color = spec.background, shape = CircleShape)
            .semantics { contentDescription = cd },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = spec.letter,
            color = spec.foreground,
            style = MaterialTheme.typography.titleMedium,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}

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
internal fun DiamondIcon(
    spec: LineIconSpec,
    size: Dp,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = remember(size) { (size.value * 0.56f).sp },
    fontWeight: FontWeight = FontWeight.Bold,
) {
    val cd = spec.contentDescription ?: "${spec.letter} line"
    Box(
        modifier = modifier
            .size(size)
            .background(color = spec.background, shape = DiamondShape)
            .semantics { contentDescription = cd },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = spec.letter,
            color = spec.foreground,
            style = MaterialTheme.typography.titleMedium,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun LetterIcon_FullScreen_Preview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column() {
                CircleIcon(
                    spec = LineIconSpec(
                        letter = "A",
                        background = Color(0xFF0039A6),
                    ),
                    size = 48.dp,
                )
                DiamondIcon(
                    spec = LineIconSpec(
                        letter = "6",
                        background = Color(0xFF00933C),
                    ),
                    size = 48.dp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CircleIcon_Preview() {
    MaterialTheme {
        CircleIcon(
            spec = LineIconSpec(
                letter = "A",
                background = Color(0xFF0039A6),
            ),
            size = 48.dp,
        )
    }
}

@Preview
@Composable
private fun DiamondIcon_Preview() {
    MaterialTheme {
        DiamondIcon(
            spec = LineIconSpec(
                letter = "F",
                background = Color(0xFFD52B1E),
            ),
            size = 48.dp,
        )
    }
}
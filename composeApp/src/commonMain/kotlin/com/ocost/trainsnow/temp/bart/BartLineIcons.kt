package com.ocost.trainsnow.temp.bart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ocost.trainsnow.shared.LineIcon
import com.ocost.trainsnow.shared.LineIconShape
import com.ocost.trainsnow.shared.LineIconSize
import org.jetbrains.compose.ui.tooling.preview.Preview

object BartLineIcons {
    @Composable
    private fun BartLineIconFactory(
        line: String,
        size: LineIconSize,
        background: Color,
        foreground: Color,
        contentDescription: String,
    ) {
        LineIcon(
            character = line,
            size = size,
            shape = LineIconShape.RoundedSquare,
            contentDescription = contentDescription,
            background = background,
            foreground = foreground,
        )
    }

    object BlueLine {
        const val LINE = "B"
        val background = Color(color = 0xFF00AEF0)
        const val CONTENT_DESC = "Blue Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.White,
                contentDescription = CONTENT_DESC,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.White,
                contentDescription = CONTENT_DESC,
            )
        }
    }

    object GreenLine {
        const val LINE = "G"
        val background = Color(0xFF4DB848)
        const val CONTENT_DESC = "Green Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.White,
                contentDescription = CONTENT_DESC,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.White,
                contentDescription = CONTENT_DESC,
            )
        }
    }

    object OrangeLine {
        const val LINE = "O"
        val background = Color(0xFFFAA61A)
        const val CONTENT_DESC = "Orange Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.Black,
                contentDescription = CONTENT_DESC,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.Black,
                contentDescription = CONTENT_DESC,
            )
        }
    }

    object RedLine {
        const val LINE = "R"
        val background = Color(0xFFED1C24)
        const val CONTENT_DESC = "Red Line"
        @Composable
        fun Large() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.White,
                contentDescription = CONTENT_DESC,
            )
        }
        @Composable
        fun Small() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.White,
                contentDescription = CONTENT_DESC,
            )
        }
    }

    object YellowLine {
        const val LINE = "Y"
        val background = Color(0xFFFFE800)
        const val CONTENT_DESC = "Yellow Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.Black,
                contentDescription = CONTENT_DESC,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = LINE,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.Black,
                contentDescription = CONTENT_DESC,
            )
        }
    }
}
// TODO: Add OAK airport shuttle

@Preview
@Composable
private fun PreviewIcons() {
    Column {
        Row {
            BartLineIcons.BlueLine.Large()
            BartLineIcons.GreenLine.Large()
            BartLineIcons.OrangeLine.Large()
            BartLineIcons.RedLine.Large()
            BartLineIcons.YellowLine.Large()
        }
        Row {
            BartLineIcons.BlueLine.Small()
            BartLineIcons.GreenLine.Small()
            BartLineIcons.OrangeLine.Small()
            BartLineIcons.RedLine.Small()
            BartLineIcons.YellowLine.Small()
        }
    }
}

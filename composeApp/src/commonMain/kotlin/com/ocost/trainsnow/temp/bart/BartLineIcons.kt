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
        val line = "B"
        val background = Color(0xFF00AEF0)
        val contentDescription = "Blue Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.White,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.White,
                contentDescription = contentDescription,
            )
        }
    }

    object GreenLine {
        val line = "G"
        val background = Color(0xFF4DB848)
        val contentDescription = "Green Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.White,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.White,
                contentDescription = contentDescription,
            )
        }
    }

    object OrangeLine {
        val line = "O"
        val background = Color(0xFFFAA61A)
        val contentDescription = "Orange Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.Black,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.Black,
                contentDescription = contentDescription,
            )
        }
    }

    object RedLine {
        val line = "R"
        val background = Color(0xFFED1C24)
        val contentDescription = "Red Line"
        @Composable
        fun Large() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.White,
                contentDescription = contentDescription,
            )
        }
        @Composable
        fun Small() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.White,
                contentDescription = contentDescription,
            )
        }
    }

    object YellowLine {
        val line = "Y"
        val background = Color(0xFFFFE800)
        val contentDescription = "Yellow Line"

        @Composable
        fun Large() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = background,
                foreground = Color.Black,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            BartLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = background,
                foreground = Color.Black,
                contentDescription = contentDescription,
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
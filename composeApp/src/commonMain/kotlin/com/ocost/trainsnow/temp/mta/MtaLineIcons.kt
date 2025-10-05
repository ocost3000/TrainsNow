package com.ocost.trainsnow.temp.mta

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ocost.trainsnow.shared.LineIcon
import com.ocost.trainsnow.shared.LineIconShape
import com.ocost.trainsnow.shared.LineIconSize
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class MtaColors(val color: Color) {
    LightBlue(Color(color = 0xFF0062CF)),
    Orange(Color(color = 0xFFEB6800)),
    LightGreen(Color(color = 0xFF799534)),
    Brown(Color(color = 0xFF8E5C33)),
    Grey(Color(color = 0xFF7C858C)),
    Yellow(Color(color = 0xFFF6BC26)),
    Red(Color(color = 0xFFD82233)),
    DarkGreen(Color(color = 0xFF009952)),
    Purple(Color(color = 0xFF9A38A1)),
    Teal(Color(color = 0xFF008EB7)),
    DarkBlue(Color(color = 0xFF08179C)),
}


object MtaLineIcons {
    @Composable
    fun MtaLineIconFactory(
        line: String,
        size: LineIconSize,
        background: Color,
        contentDescription: String,
        textColor: Color = Color.White,
        shape: LineIconShape = LineIconShape.Circle,
    ) {
        LineIcon(
            character = line,
            size = size,
            shape = shape,
            contentDescription = contentDescription,
            background = background,
            foreground = textColor,
        )
    }

    object ALine {
        val line = "A"
        val color = MtaColors.LightBlue.color
        val contentDescription = "A Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object BLine {
        val line = "B"
        val color = MtaColors.Orange.color
        val contentDescription = "B Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object CLine {
        val line = "C"
        val color = MtaColors.LightBlue.color
        val contentDescription = "C Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object DLine {
        val line = "D"
        val color = MtaColors.Orange.color
        val contentDescription = "D Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object ELine {
        val line = "E"
        val color = MtaColors.LightBlue.color
        val contentDescription = "E Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object FLine {
        val line = "F"
        val color = MtaColors.Orange.color
        val contentDescription = "F Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object FLineExp {
        val line = "F"
        val color = MtaColors.Orange.color
        val contentDescription = "F Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                shape = LineIconShape.Diamond,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                shape = LineIconShape.Diamond,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object GLine {
        val line = "G"
        val color = MtaColors.LightGreen.color
        val contentDescription = "G Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object JLine {
        val line = "J"
        val color = MtaColors.Brown.color
        val contentDescription = "J Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object LLine {
        val line = "L"
        val color = MtaColors.Grey.color
        val contentDescription = "L Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object MLine {
        val line = "M"
        val color = MtaColors.Orange.color
        val contentDescription = "M Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object NLine {
        val line = "N"
        val color = MtaColors.Yellow.color
        val contentDescription = "N Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }
    }

    object QLine {
        val line = "Q"
        val color = MtaColors.Yellow.color
        val contentDescription = "Q Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }
    }

    object RLine {
        val line = "R"
        val color = MtaColors.Yellow.color
        val contentDescription = "R Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }
    }

    object WLine {
        val line = "W"
        val color = MtaColors.Yellow.color
        val contentDescription = "W Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
                textColor = Color.Black,
            )
        }
    }

    object ZLine {
        val line = "Z"
        val color = MtaColors.Brown.color
        val contentDescription = "Z Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object OneLine {
        val line = "1"
        val color = MtaColors.Red.color
        val contentDescription = "1 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object TwoLine {
        val line = "2"
        val color = MtaColors.Red.color
        val contentDescription = "2 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object ThreeLine {
        val line = "3"
        val color = MtaColors.Red.color
        val contentDescription = "3 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object FourLine {
        val line = "4"
        val color = MtaColors.DarkGreen.color
        val contentDescription = "4 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object FiveLine {
        val line = "5"
        val color = MtaColors.DarkGreen.color
        val contentDescription = "5 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object SixLine {
        val line = "6"
        val color = MtaColors.DarkGreen.color
        val contentDescription = "6 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }

    object SixLineExp {
        val line = "6"
        val color = MtaColors.DarkGreen.color
        val contentDescription = "6 Express Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
                shape = LineIconShape.Diamond,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
                shape = LineIconShape.Diamond,
            )
        }
    }

    object SevenLine {
        val line = "7"
        val color = MtaColors.Purple.color
        val contentDescription = "7 Line"

        @Composable
        fun Large() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Large,
                background = color,
                contentDescription = contentDescription,
            )
        }

        @Composable
        fun Small() {
            MtaLineIconFactory(
                line = line,
                size = LineIconSize.Small,
                background = color,
                contentDescription = contentDescription,
            )
        }
    }
}

@Preview
@Composable
fun PreviewIcons() {
    Column {
        // Light Blue lines
        Row {
            MtaLineIcons.ALine.Large()
            MtaLineIcons.CLine.Large()
            MtaLineIcons.ELine.Large()
        }
        // Orange lines
        Row {
            MtaLineIcons.BLine.Large()
            MtaLineIcons.DLine.Large()
            MtaLineIcons.FLine.Large()
            MtaLineIcons.FLineExp.Large()
            MtaLineIcons.MLine.Large()
        }
        // Light Green, Brown, Grey lines
        Row {
            MtaLineIcons.GLine.Large()
            MtaLineIcons.JLine.Large()
            MtaLineIcons.ZLine.Large()
            MtaLineIcons.LLine.Large()
        }
        // Yellow lines (with black text)
        Row {
            MtaLineIcons.NLine.Large()
            MtaLineIcons.QLine.Large()
            MtaLineIcons.RLine.Large()
            MtaLineIcons.WLine.Large()
        }
        // Red lines
        Row {
            MtaLineIcons.OneLine.Large()
            MtaLineIcons.TwoLine.Large()
            MtaLineIcons.ThreeLine.Large()
        }
        // Dark Green lines
        Row {
            MtaLineIcons.FourLine.Large()
            MtaLineIcons.FiveLine.Large()
            MtaLineIcons.SixLine.Large()
            MtaLineIcons.SixLineExp.Large()
        }
        // Purple line
        Row {
            MtaLineIcons.SevenLine.Large()
        }
        // Light Blue lines
        Row {
            MtaLineIcons.ALine.Small()
            MtaLineIcons.CLine.Small()
            MtaLineIcons.ELine.Small()
        }
        // Orange lines
        Row {
            MtaLineIcons.BLine.Small()
            MtaLineIcons.DLine.Small()
            MtaLineIcons.FLine.Small()
            MtaLineIcons.FLineExp.Small()
            MtaLineIcons.MLine.Small()
        }
        // Light Green, Brown, Grey lines
        Row {
            MtaLineIcons.GLine.Small()
            MtaLineIcons.JLine.Small()
            MtaLineIcons.ZLine.Small()
            MtaLineIcons.LLine.Small()
        }
        // Yellow lines (with black text)
        Row {
            MtaLineIcons.NLine.Small()
            MtaLineIcons.QLine.Small()
            MtaLineIcons.RLine.Small()
            MtaLineIcons.WLine.Small()
        }
        // Red lines
        Row {
            MtaLineIcons.OneLine.Small()
            MtaLineIcons.TwoLine.Small()
            MtaLineIcons.ThreeLine.Small()
        }
        // Dark Green lines
        Row {
            MtaLineIcons.FourLine.Small()
            MtaLineIcons.FiveLine.Small()
            MtaLineIcons.SixLine.Small()
            MtaLineIcons.SixLineExp.Small()
        }
        // Purple line
        Row {
            MtaLineIcons.SevenLine.Small()
        }
    }
}
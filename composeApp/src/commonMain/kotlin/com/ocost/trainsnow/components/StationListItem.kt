package com.ocost.trainsnow.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ocost.trainsnow.temp.mta.MtaLineIcons
import org.jetbrains.compose.ui.tooling.preview.Preview

data class NextTrain(
    val id: String,
    val arrivalMinutes: Int,
    val lastStop: String,
    val lineIcon: @Composable () -> Unit
)

@Composable
fun StationList(
    trains: List<NextTrain>,
    modifier: Modifier = Modifier,
) {
    var visibleCount by remember { mutableStateOf(4) }
    val trainsToShow = trains.take(visibleCount.coerceAtMost(trains.size))
    val canShowMore = visibleCount < trains.size && visibleCount < 12

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        trainsToShow.forEachIndexed { index, train ->
            StationListItem(train)
            if (index < trainsToShow.lastIndex) {
                HorizontalDivider()
            }
        }
            Button(
                onClick = {
                    if (canShowMore) {
                        visibleCount += 4
                    } else {
                        visibleCount = 4
                    }
                },
            ) {
                if (canShowMore) {
                    Text("Show More")
                } else {
                    Text("Show Less")
                }
            }
    }
}

@Composable
fun StationListItem(
    train: NextTrain,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        train.lineIcon()
        Text(
            text = train.lastStop,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "${train.arrivalMinutes} min",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview
@Composable
fun PreviewStationListItem() {
    Surface {
        StationListItem(
            NextTrain(
                id = "1",
                arrivalMinutes = 5,
                lineIcon = { MtaLineIcons.OneLine.Small() },
                lastStop = "Van Cortland Park-242 St",
            )
        )
    }
}

@Preview
@Composable
fun PreviewStationList() {
    Surface {
        StationList(
            trains = listOf(
                NextTrain(
                    id = "e1",
                    arrivalMinutes = 2,
                    lineIcon = { MtaLineIcons.ELine.Small() },
                    lastStop = "World Trade Center",
                ),
                NextTrain(
                    id = "f1",
                    arrivalMinutes = 5,
                    lineIcon = { MtaLineIcons.FLine.Small() },
                    lastStop = "Coney Island-Stillwell Av",
                ),
                NextTrain(
                    id = "m1",
                    arrivalMinutes = 8,
                    lineIcon = { MtaLineIcons.MLine.Small() },
                    lastStop = "Metropolitan Av",
                ),
                NextTrain(
                    id = "r1",
                    arrivalMinutes = 12,
                    lineIcon = { MtaLineIcons.RLine.Small() },
                    lastStop = "Bay Ridge-95 St",
                ),
                NextTrain(
                    id = "n1",
                    arrivalMinutes = 15,
                    lineIcon = { MtaLineIcons.ELine.Small() },
                    lastStop = "World Trade Center",
                ),
                NextTrain(
                    id = "q1",
                    arrivalMinutes = 18,
                    lineIcon = { MtaLineIcons.MLine.Small() },
                    lastStop = "Metropolitan Av",
                ),
                NextTrain(
                    id = "s1",
                    arrivalMinutes = 22,
                    lineIcon = { MtaLineIcons.FLine.Small() },
                    lastStop = "Coney Island-Stillwell Av",
                ),
                NextTrain(
                    id = "w1",
                    arrivalMinutes = 25,
                    lineIcon = { MtaLineIcons.RLine.Small() },
                    lastStop = "Bay Ridge-95 St",
                ),
                NextTrain(
                    id = "b1",
                    arrivalMinutes = 28,
                    lineIcon = { MtaLineIcons.ELine.Small() },
                    lastStop = "World Trade Center",
                ),
                NextTrain(
                    id = "d1",
                    arrivalMinutes = 32,
                    lineIcon = { MtaLineIcons.RLine.Small() },
                    lastStop = "Bay Ridge-95 St",
                ),
                NextTrain(
                    id = "g1",
                    arrivalMinutes = 35,
                    lineIcon = { MtaLineIcons.FLine.Small() },
                    lastStop = "Coney Island-Stillwell Av",
                ),
                NextTrain(
                    id = "j1",
                    arrivalMinutes = 38,
                    lineIcon = { MtaLineIcons.MLine.Small() },
                    lastStop = "Metropolitan Av",
                )
            )
        )
    }
}
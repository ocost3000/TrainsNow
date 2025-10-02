package com.ocost.trainsnow

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        // Tabs definition
        val tabs = remember { listOf(BottomTab.Shortcuts, BottomTab.Subway, BottomTab.Bus) }

        // Each tab keeps its own navigation stack of route titles
        val listSaver = remember {
            Saver<List<String>, List<String>>(
                save = { it },
                restore = { it.toList() },
            )
        }

        var currentTab by rememberSaveable { mutableStateOf(BottomTab.Shortcuts) }

        // rememberSaveable stacks keyed by tab
        var shortcutsStack by rememberSaveable(stateSaver = listSaver) { mutableStateOf(listOf(BottomTab.Shortcuts.title)) }
        var subwayStack by rememberSaveable(stateSaver = listSaver) { mutableStateOf(listOf(BottomTab.Subway.title)) }
        var busStack by rememberSaveable(stateSaver = listSaver) { mutableStateOf(listOf(BottomTab.Bus.title)) }

        fun currentStack(): List<String> = when (currentTab) {
            BottomTab.Shortcuts -> shortcutsStack
            BottomTab.Subway -> subwayStack
            BottomTab.Bus -> busStack
        }

        fun setCurrentStack(newStack: List<String>) {
            when (currentTab) {
                BottomTab.Shortcuts -> shortcutsStack = newStack
                BottomTab.Subway -> subwayStack = newStack
                BottomTab.Bus -> busStack = newStack
            }
        }

        val canPop = currentStack().size > 1
        val currentTitle = currentStack().lastOrNull() ?: currentTab.title

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = currentTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    navigationIcon = {
                        if (canPop) {
                            // Use simple text as back icon to avoid platform-specific icon deps
                            Text(
                                text = "< Back",
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 8.dp)
                                    .padding(bottom = 8.dp)
                                    .then(Modifier),
                            )
                        }
                    },
                )
            },
            bottomBar = {
                NavigationBar {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            selected = currentTab == tab,
                            onClick = { currentTab = tab },
                            icon = tab.icon,
                            label = { Text(tab.title) },
                        )
                    }
                }
            },
        ) { innerPadding ->
            when (currentTab) {
                BottomTab.Shortcuts -> ShortcutsScreen(
                    stack = currentStack(),
                    onPush = { route -> setCurrentStack(currentStack() + route) },
                    onPop = { if (currentStack().size > 1) setCurrentStack(currentStack().dropLast(1)) },
                    padding = innerPadding,
                    canPop = canPop,
                )
                BottomTab.Subway -> SubwayScreen(
                    stack = currentStack(),
                    onPush = { route -> setCurrentStack(currentStack() + route) },
                    onPop = { if (currentStack().size > 1) setCurrentStack(currentStack().dropLast(1)) },
                    padding = innerPadding,
                    canPop = canPop,
                )
                BottomTab.Bus -> BusScreen(
                    stack = currentStack(),
                    onPush = { route -> setCurrentStack(currentStack() + route) },
                    onPop = { if (currentStack().size > 1) setCurrentStack(currentStack().dropLast(1)) },
                    padding = innerPadding,
                    canPop = canPop,
                )
            }
        }
    }
}

private enum class BottomTab(
    val title: String,
    val icon: @Composable () -> Unit
) {
    Shortcuts(
        title = "Shortcuts",
        icon = { Text("⭐") }
    ),
    Subway(
        title = "Subway",
        icon = { Text("🚇") }
    ),
    Bus(
        title = "Bus",
        icon = { Text("🚌") }
    )
}

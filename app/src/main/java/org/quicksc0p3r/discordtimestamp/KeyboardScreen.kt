package org.quicksc0p3r.discordtimestamp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.datetime.Clock
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardScreen() {
    val keysMatrix = arrayOf(
        arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "9"),
        arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"),
        arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l"),
        arrayOf("^", "z", "x", "c", "v", "b", "n", "m", "<x")
    )
    val keysMatrixUpper = arrayOf(
        arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "9"),
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Z", "X", "C", "V", "B", "N", "M", ",", ".")
    )

    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(CurrentScreen.MENU) }
    var timestampType by remember { mutableStateOf(TimestampType.SHORT_TIME) }
    var constructTimestamp by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    val datePickerState = rememberDatePickerState()

    SideEffect {
        if (constructTimestamp) {
            val timestamp = constructTimestamp(
                timestampType,
                timePickerState.hour to timePickerState.minute,
                if (datePickerState.selectedDateMillis != null)
                    datePickerState.selectedDateMillis!!
                else Clock.System.now().toEpochMilliseconds()
            )
            (context as IMEService).currentInputConnection.commitText(
                timestamp,
                timestamp.length
            )
            constructTimestamp = false
        }
    }
    Crossfade(currentScreen, label = "") { currentScreenValue ->
        when (currentScreenValue) {
            CurrentScreen.MENU -> Menu(
                changeCurrentScreen = { currentScreen = it },
                changeTimestampType = { timestampType = it }
            )

            CurrentScreen.TIME_PICKER_NEXT_MENU, CurrentScreen.TIME_PICKER_NEXT_DATE ->
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ListItem(
                        leadingContent = {
                            IconButton(onClick = { currentScreen = CurrentScreen.MENU }) {
                                Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
                            }
                        },
                        headlineContent = {}
                    )
                    TimePicker(timePickerState)
                    ListItem(
                        trailingContent = {
                            TextButton(onClick = {
                                if (currentScreen == CurrentScreen.TIME_PICKER_NEXT_DATE)
                                    currentScreen = CurrentScreen.DATE_PICKER
                                else {
                                    constructTimestamp = true
                                    currentScreen = CurrentScreen.MENU
                                }
                            }) {
                                Text(if (currentScreen == CurrentScreen.TIME_PICKER_NEXT_DATE) "Next" else "Done")
                            }
                        },
                        headlineContent = {}
                    )
                }

            CurrentScreen.DATE_PICKER ->
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ListItem(
                        leadingContent = {
                            IconButton(onClick = {
                                currentScreen = CurrentScreen.TIME_PICKER_NEXT_DATE
                            }) {
                                Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
                            }
                        },
                        headlineContent = {}
                    )
                    DatePicker(datePickerState)
                    ListItem(
                        trailingContent = {
                            TextButton(onClick = {
                                constructTimestamp = true
                                currentScreen = CurrentScreen.MENU
                            }) {
                                Text("Done")
                            }
                        },
                        headlineContent = {}
                    )
                }
            //todo add current screen for regular keyboard here
            CurrentScreen.KEYBOARD ->
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                ) {
                    keysMatrix.forEach { row ->
                        FixedHeightBox(modifier = Modifier.fillMaxWidth(), height = 56.dp) {
                            Row(Modifier) {
                                row.forEach { key ->

                                    KeyboardKey(
                                        keyboardKey = key,
                                        modifier = Modifier.weight(1f),
                                        currentScreen
                                    )
                                }

                            }
                        }
                    }
                }

            CurrentScreen.KEYBOARD_UPPER ->
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                ) {

                    keysMatrix.forEach { row ->
                        FixedHeightBox(modifier = Modifier.fillMaxWidth(), height = 56.dp) {
                            Row(Modifier) {
                                row.forEach { key ->
                                    KeyboardKey(
                                        keyboardKey = key,
                                        modifier = Modifier.weight(1f),
                                        currentScreen = currentScreen
                                    )
                                }
                            }
                        }
                    }

                }
        }
    }
}

@Composable
fun FixedHeightBox(modifier: Modifier, height: Dp, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        val h = height.roundToPx()
        layout(constraints.maxWidth, h) {
            placeables.forEach { placeable ->
                placeable.place(x = 0, y = kotlin.math.min(0, h - placeable.height))
            }
        }
    }
}

@Composable
fun KeyboardKey(
    keyboardKey: String,
    modifier: Modifier,
    currentScreen: CurrentScreen
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState()
    val ctx = LocalContext.current
    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
        Text(
            keyboardKey,
            Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .border(1.dp, Color.Black)
                .clickable(interactionSource = interactionSource, indication = null) {
                    (ctx as IMEService).currentInputConnection.commitText(
                        keyboardKey,
                        keyboardKey
                            .length
                    )
                }
                .background(Color.White)
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )

        )
        if (pressed.value) {
            if (keyboardKey == "^") {
                //currentScreen = CurrentScreen.KEYBOARD_UPPER
            } else {
                Text(
                    keyboardKey,
                    Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .background(Color.White)
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = 48.dp
                        ),
                )
            }
        }
    }
}
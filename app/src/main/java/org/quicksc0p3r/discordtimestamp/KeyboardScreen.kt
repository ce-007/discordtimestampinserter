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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardScreen() {
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
                        .background(MaterialTheme.colorScheme.primaryContainer)
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
                            } ) {
                                Text(if (currentScreen == CurrentScreen.TIME_PICKER_NEXT_DATE) "Next" else "Done")
                            }
                        },
                        headlineContent = {}
                    )
                }
            CurrentScreen.DATE_PICKER ->
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ListItem(
                        leadingContent = {
                            IconButton(onClick = { currentScreen = CurrentScreen.TIME_PICKER_NEXT_DATE } ) {
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
                            } ) {
                                Text("Done")
                            }
                        },
                        headlineContent = {}
                    )
                }
        }
    }
}
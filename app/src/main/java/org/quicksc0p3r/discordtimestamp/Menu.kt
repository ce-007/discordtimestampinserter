package org.quicksc0p3r.discordtimestamp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Menu(changeCurrentScreen: (CurrentScreen) -> Unit, changeTimestampType: (TimestampType) -> Unit) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 250.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_MENU)
                changeTimestampType(TimestampType.SHORT_TIME)
            } ) {
                Text("Short time")
            }
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_MENU)
                changeTimestampType(TimestampType.LONG_TIME)
            } ) {
                Text("Long time")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_DATE)
                changeTimestampType(TimestampType.SHORT_DATE)
            } ) {
                Text("Short date")
            }
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_DATE)
                changeTimestampType(TimestampType.LONG_DATE)
            } ) {
                Text("Long date")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_DATE)
                changeTimestampType(TimestampType.SHORT_TIME_DATE)
            } ) {
                Text("Short date/time")
            }
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_DATE)
                changeTimestampType(TimestampType.LONG_TIME_DATE)
            } ) {
                Text("Long date/time")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.TIME_PICKER_NEXT_DATE)
                changeTimestampType(TimestampType.RELATIVE)
            } ) {
                Text("Relative")
            }
        }
    }
}
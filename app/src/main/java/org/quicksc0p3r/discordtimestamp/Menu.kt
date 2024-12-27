package org.quicksc0p3r.discordtimestamp

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(changeCurrentScreen: (CurrentScreen) -> Unit, changeTimestampType: (TimestampType) -> Unit) {
    val context = LocalContext.current
    val inputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

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
            FilledTonalButton(onClick = {
                changeCurrentScreen(CurrentScreen.KEYBOARD)
                changeTimestampType(TimestampType.REGULAR)
            } ) {
                Text("Keyboard")
            }
        }
    }
}
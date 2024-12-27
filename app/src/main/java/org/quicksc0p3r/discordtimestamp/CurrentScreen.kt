package org.quicksc0p3r.discordtimestamp

import androidx.compose.runtime.MutableState

enum class CurrentScreen : MutableState<CurrentScreen> {
    MENU,
    TIME_PICKER_NEXT_DATE,
    TIME_PICKER_NEXT_MENU,
    DATE_PICKER,
    KEYBOARD,
    KEYBOARD_UPPER
}
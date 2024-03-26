package org.quicksc0p3r.discordtimestamp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import org.quicksc0p3r.discordtimestamp.ui.theme.DiscordTimestampInserterTheme

class ComposeKeyboardView(
    context: Context
) : AbstractComposeView(context) {

    @Composable
    override fun Content() {
        DiscordTimestampInserterTheme {
            KeyboardScreen()
        }
    }

}

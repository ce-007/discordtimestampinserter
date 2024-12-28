package org.ce_007.keyboardwithdiscordtimestamp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import org.ce_007.keyboardwithdiscordtimestamp.ui.theme.DiscordTimestampInserterTheme

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

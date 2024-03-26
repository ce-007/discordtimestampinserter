package org.quicksc0p3r.discordtimestamp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView

class ComposeKeyboardView(
    context: Context
) : AbstractComposeView(context) {

    @Composable
    override fun Content() {
        KeyboardScreen()
    }

}

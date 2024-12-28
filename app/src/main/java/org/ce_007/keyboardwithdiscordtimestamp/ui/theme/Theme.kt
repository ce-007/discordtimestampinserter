package org.ce_007.keyboardwithdiscordtimestamp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun DiscordTimestampInserterTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val systemUIController = rememberSystemUiController()
    val colorScheme = if (isSystemInDarkTheme())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            dynamicDarkColorScheme(context)
        else
            PurpleDarkColorScheme
    else
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            dynamicLightColorScheme(context)
        else
            PurpleLightColorScheme
    SideEffect {
        systemUIController.setSystemBarsColor(color = colorScheme.background)
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
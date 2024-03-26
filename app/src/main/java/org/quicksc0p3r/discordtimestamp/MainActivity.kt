package org.quicksc0p3r.discordtimestamp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ToggleOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.quicksc0p3r.discordtimestamp.ui.theme.DiscordTimestampInserterTheme
import splitties.systemservices.inputMethodManager

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscordTimestampInserterTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = { TopAppBar(title = { Text("Discord Timestamp Inserter") }) }
                    ) { padding ->
                        Column(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxSize()
                        ) {
                            val context = LocalContext.current
                            ExtendedFloatingActionButton(
                                text = { Text("Enable input method") },
                                icon = {
                                    Icon(Icons.Rounded.ToggleOn, null)
                                },
                                onClick = { context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)) })
                        }
                    }
                }
            }
        }
    }
}
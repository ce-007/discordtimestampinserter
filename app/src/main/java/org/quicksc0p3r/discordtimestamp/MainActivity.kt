package org.quicksc0p3r.discordtimestamp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscordTimestampInserterTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column {
                        Options()
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            isSystemInDarkTheme()
        }
    }
}

@Composable
fun Options() {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val ctx = LocalContext.current
        Text(text = "Compose Keyboard")
        val (text, setValue) = remember { mutableStateOf(TextFieldValue("Try here")) }
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            ctx.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }) {
            Text(text = "Enable IME")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            inputMethodManager.showInputMethodPicker()
        }) {
            Text(text = "Select IME")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = text, onValueChange = setValue, modifier = Modifier.fillMaxWidth())
    }
}

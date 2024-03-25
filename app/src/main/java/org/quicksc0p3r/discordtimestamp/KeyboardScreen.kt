package org.quicksc0p3r.discordtimestamp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardScreen() {
    var dialogTest by remember { mutableStateOf(false) }
    if (dialogTest)
        AlertDialog(
            onDismissRequest = { dialogTest = false },
            title = { Text("Dialog test") },
            confirmButton = { TextButton(onClick = { dialogTest = false }) { Text("Close") } }
        )
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(300.dp)
    ) {
        
    }
}
package org.ce_007.keyboardwithdiscordtimestamp

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.ToggleOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.quicksc0p3r.discordtimestamp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text("Discord Timestamp Inserter") },
            actions = {
                IconButton(onClick = { navController.navigate(NavRoutes.About.route) }) {
                    Icon(Icons.Outlined.Info, stringResource(R.string.about))
                }
            }
        ) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
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
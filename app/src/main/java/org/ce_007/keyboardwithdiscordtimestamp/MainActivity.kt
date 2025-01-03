package org.ce_007.keyboardwithdiscordtimestamp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ce_007.keyboardwithdiscordtimestamp.ui.theme.DiscordTimestampInserterTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            DiscordTimestampInserterTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Main.route
    ) {
        composable(NavRoutes.Main.route) { Main(navController) }
        composable(NavRoutes.About.route) { About(navController) }
    }
}
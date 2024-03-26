package org.quicksc0p3r.discordtimestamp

sealed class NavRoutes(val route: String) {
    object Main: NavRoutes("main")
    object About: NavRoutes("about")
}
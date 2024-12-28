package org.ce_007.keyboardwithdiscordtimestamp

sealed class NavRoutes(val route: String) {
    object Main: NavRoutes("main")
    object About: NavRoutes("about")
}
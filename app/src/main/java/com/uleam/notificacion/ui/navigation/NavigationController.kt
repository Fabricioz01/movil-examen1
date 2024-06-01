package com.uleam.notificacion.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.uleam.notificacion.ui.notificacion.NotificacionDestination
import com.uleam.notificacion.ui.notificacion.NotificacionScreen
import com.uleam.notificacion.ui.vistas.VistasDestination
import com.uleam.notificacion.ui.vistas.VistasScreen

@Composable
fun NavigationController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NotificacionDestination.route,
        modifier = modifier
    ) {
        composable(route = NotificacionDestination.route) {
            NotificacionScreen(
                navigateToItemUpdate = { itemId ->
                    navController.navigate("${VistasDestination.route}/$itemId")
                }
            )
        }
        composable(
            route = "${VistasDestination.route}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getInt("itemId") ?: 0
            VistasScreen(itemId, onBack = { navController.navigate(NotificacionDestination.route) })
        }
    }
}

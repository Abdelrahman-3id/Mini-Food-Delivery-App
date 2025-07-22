package com.example.minifooddeliveryapp.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minifooddeliveryapp.presentation.ui.OrderDetailScreen
import com.example.minifooddeliveryapp.presentation.ui.OrderListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "order_list"
    ) {
        composable("order_list") {
            OrderListScreen { orderId ->
                navController.navigate("order_detail/$orderId")
            }
        }
        composable(
            route = "order_detail/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType }
            )) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderDetailScreen(
                orderId = orderId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
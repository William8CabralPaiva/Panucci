package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.CheckoutScreen
import br.com.alura.panucci.ui.screens.DrinksListScreen
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.screens.MenuListScreen
import br.com.alura.panucci.ui.screens.ProductDetailsScreen

@Composable
fun PanucciNavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = AppDestination.Highlight.route
    ) {
        composable(AppDestination.Highlight.route) {
            HighlightsListScreen(
                products = sampleProducts,
                onNavigateToDetails = {
                    navController.navigate(getProductDetailsRoute(it.id))
                },
                onNavigateToCheckout = {
                    navController.navigate(AppDestination.Checkout.route)
                }
            )
        }
        composable(AppDestination.Menu.route) {
            MenuListScreen(
                products = sampleProducts,
                onNavigateToDetails = {
                    navController.navigate(getProductDetailsRoute(it.id))
                }
            )
        }
        composable(AppDestination.Drinks.route) {
            DrinksListScreen(
                products = sampleProducts,
                onNavigateToDetails = {
                    navController.navigate(getProductDetailsRoute(it.id))
                }
            )
        }
        composable(
            "${AppDestination.ProductDetails.route}/{productId}"
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productId")
            sampleProducts.find {
                it.id == id
            }?.let { product ->
                ProductDetailsScreen(
                    product = product,
                    onNavigateToCheckout = {
                        navController.navigate(AppDestination.Checkout.route)
                    },
                )
            } ?: LaunchedEffect(Unit) {
                navController.navigateUp()
            }
        }
        composable(AppDestination.Checkout.route) {
            CheckoutScreen(
                products = sampleProducts,
                onPopBackStack = {
                    // msm coisa do pop diferença é que ele tem integração com o deeplink
                    navController.navigateUp()
                }
            )
        }
    }
}

fun getProductDetailsRoute(id: String): String {
    return "${AppDestination.ProductDetails.route}/${id}"
}
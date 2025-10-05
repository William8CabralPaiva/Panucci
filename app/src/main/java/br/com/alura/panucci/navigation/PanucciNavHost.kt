package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import br.com.alura.panucci.ui.components.BottomAppBarItem


@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = highlightsListRoute
    ) {
        highlightsListScreen(navController)
        menuScreen(navController)
        drinksScreen(navController)
        productDetailsScreen(navController)
        checkoutScreen(navController)
    }

}

fun NavController.navigateToSingleTop(
    item: BottomAppBarItem,
) {
    //                            val route = it.destination
//                            if (route != currentDestination?.route) { // só navega se for diferente
//                                navController.navigate(route) {
//                                    launchSingleTop = true
//                                    popUpTo(navController.graph.startDestinationId) {
//                                        saveState = true
//                                    }
//                                    restoreState = true
//                                }
//                            }
    val (route, navigate) = when (item) {
        BottomAppBarItem.Drinks -> Pair(
            drinksRoute,
            ::navigateToDrinks
        )

        BottomAppBarItem.HighlightsList -> Pair(
            highlightsListRoute,
            ::navigateToHighlightsList
        )

        BottomAppBarItem.Menu -> Pair(
            menuRoute,
            ::navigateToMenu
        )
    }

    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route) {
            saveState = true
        }
        restoreState = true
    }
    navigate(navOptions)
}
package com.app.gameexplorer.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.gameexplorer.presentation.game_details.GameDetailScreen
import com.app.gameexplorer.presentation.games_list.GamesListScreen

sealed class Screen(val route: String) {
    object GamesList : Screen("games_list")
    object GameDetail : Screen("game_detail/{gameId}") {
        fun passId(id: Int): String = "game_detail/$id"
    }
}

@Composable
fun GameExplorerNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.GamesList.route
    ) {
        composable(route = Screen.GamesList.route) {
            GamesListScreen(
                onNavigateToDetail = { gameId ->
                    navController.navigate(Screen.GameDetail.passId(gameId))
                }
            )
        }


        composable(
            route = Screen.GameDetail.route,
            arguments = listOf(
                navArgument("gameId") { type = NavType.IntType }
            )
        ) {
            GameDetailScreen()
        }
    }
}
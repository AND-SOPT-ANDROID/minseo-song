package org.sopt.and.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.and.presentation.ui.component.bar.BottomBar
import org.sopt.and.presentation.ui.home.HomeScreen
import org.sopt.and.presentation.ui.my.MyScreen
import org.sopt.and.presentation.ui.search.SearchScreen
import org.sopt.and.presentation.ui.signin.SignInScreen
import org.sopt.and.presentation.ui.signup.SignUpScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            val selectedScreen = ScreenTab.entries.find { it.route == currentRoute }
            if (selectedScreen != null) {
                BottomBar(selected = selectedScreen, navController = navController)
            } else if (currentRoute in listOf(
                    Routes.Home.route,
                    Routes.My.route,
                    Routes.Search.route
                )
            ) {
                BottomBar(selected = null, navController = navController)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            NavHost(navController = navController, startDestination = Routes.SignIn.route) {
                composable(Routes.SignIn.route) {
                    SignInScreen(
                        navController
                    )
                }
                composable(Routes.SignUp.route) {
                    SignUpScreen(
                        navController
                    )
                }
                composable(Routes.My.route) {
                    MyScreen(
                        navController
                    )
                }
                composable(Routes.Search.route) { SearchScreen(navController) }
                composable(Routes.Home.route) { HomeScreen(navController) }
            }
        }
    }
}
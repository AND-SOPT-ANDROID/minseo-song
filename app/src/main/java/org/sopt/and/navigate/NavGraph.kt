package org.sopt.and.navigate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.and.component.bar.BottomBar
import org.sopt.and.ui.home.HomeScreen
import org.sopt.and.ui.my.MyScreen
import org.sopt.and.ui.search.SearchScreen
import org.sopt.and.ui.signin.SignInScreen
import org.sopt.and.ui.signin.SignInViewModel
import org.sopt.and.ui.signup.SignUpScreen
import org.sopt.and.ui.signup.SignUpViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val signUpViewModel: SignUpViewModel = viewModel()
    val signInViewModel: SignInViewModel = viewModel()

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
                        navController,
                        signInViewModel = signInViewModel
                    )
                }
                composable(Routes.SignUp.route) {
                    SignUpScreen(
                        navController,
                        signUpViewModel = signUpViewModel
                    )
                }
                composable(Routes.My.route) {
                    MyScreen(
                        navController,
                        signInViewModel = signInViewModel
                    )
                }
                composable(Routes.Search.route) { SearchScreen(navController) }
                composable(Routes.Home.route) { HomeScreen(navController) }
            }
        }
    }
}
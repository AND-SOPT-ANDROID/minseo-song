package org.sopt.and.navigate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.model.Routes
import org.sopt.and.ui.home.HomeScreen
import org.sopt.and.ui.my.MyScreen
import org.sopt.and.ui.search.SearchScreen
import org.sopt.and.ui.signin.SignInScreen
import org.sopt.and.ui.signin.SignInViewModel
import org.sopt.and.ui.signup.SignUpScreen
import org.sopt.and.ui.signup.SignUpViewModel

@Composable
fun NavGraph(navController: NavHostController){
    val signUpViewModel: SignUpViewModel = viewModel()
    val signInViewModel: SignInViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.My.route){
        composable(route = Routes.SignIn.route){
            SignInScreen(navController, signInViewModel = signInViewModel, signUpViewModel = signUpViewModel)
        }
        composable(route = Routes.SignUp.route){
            SignUpScreen(navController, viewModel = signUpViewModel)
        }
        composable(route = Routes.My.route){
            MyScreen(navController, signInViewModel = signInViewModel)
        }
        composable(route = Routes.Search.route){
            SearchScreen(navController)
        }
        composable(route = Routes.Home.route){
            HomeScreen(navController)
        }
    }
}
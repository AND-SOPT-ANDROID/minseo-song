package org.sopt.and.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.model.Routes
import org.sopt.and.ui.my.MyScreen
import org.sopt.and.ui.signin.SignInScreen
import org.sopt.and.ui.signup.SignUpScreen

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.SignIn.route){
        composable(route = Routes.SignIn.route){
            SignInScreen(navController)
        }
        composable(route = Routes.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = Routes.My.route){
            MyScreen(navController)
        }
        composable(route = Routes.Search.route){
            TODO("검색화면")
        }
        composable(route = Routes.Home.route){
            TODO("홈화면")
        }
    }
}
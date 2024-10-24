package org.sopt.and.model

sealed class Routes(val route: String) {
    object SignIn : Routes("SignIn")
    object SignUp : Routes("SignUp")
    object My : Routes("My")
    object Search : Routes("Search")
    object Home : Routes("Home")
}
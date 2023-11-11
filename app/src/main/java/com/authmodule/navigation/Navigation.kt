package com.authmodule.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.authmodule.presentation.signin.SignInScreen
import com.authmodule.presentation.signin.SignInViewModel
import com.authmodule.presentation.signup.SignUpScreen
import com.authmodule.presentation.signup.SignUpViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.SignInScreen.route
    ){
        composable(ScreenRoutes.SignInScreen.route){
            val viewModel = SignInViewModel()
            SignInScreen(viewModel)
        }
        composable(ScreenRoutes.SignUpScreen.route){
            val viewModel = SignUpViewModel()
            SignUpScreen()
        }
    }

}

sealed class ScreenRoutes(val route:String){
    object SignInScreen:ScreenRoutes("sign_in_screen")
    object SignUpScreen:ScreenRoutes("sign_up_screen")
}
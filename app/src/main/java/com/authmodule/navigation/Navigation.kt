package com.authmodule.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.authmodule.presentation.signin.SignInScreen
import com.authmodule.presentation.signin.SignInUIEvent
import com.authmodule.presentation.signin.SignInViewModel
import com.authmodule.presentation.signup.SignUpScreen
import com.authmodule.presentation.signup.SignUpUIEvent
import com.authmodule.presentation.signup.SignUpViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.SignUpScreen.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(ScreenRoutes.SignInScreen.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            SignInScreen(viewModel,
                onSignUpBtnClick = {
                    navController.popBackStack()
                },
                onSignInBtnClick = {
                    viewModel.onEvent(SignInUIEvent.SignInBtnClicked)
                })
        }
        composable(ScreenRoutes.SignUpScreen.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(viewModel,
                onLoginTxtClick = {
                    navController.navigate(ScreenRoutes.SignInScreen.route) {}
                },
                onSignUpBtnClick = {
                    viewModel.onEvent(SignUpUIEvent.SignUpBtnClicked)
                })
        }
    }

}

sealed class ScreenRoutes(val route: String) {
    object SignInScreen : ScreenRoutes("sign_in_screen")
    object SignUpScreen : ScreenRoutes("sign_up_screen")
}
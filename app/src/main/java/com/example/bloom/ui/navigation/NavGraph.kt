package com.example.bloom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bloom.ui.screens.SplashScreen
import com.example.bloom.ui.screens.home.HomeScreen
import com.example.bloom.ui.screens.onbarding.OnboardingScreen1
import com.example.bloom.ui.screens.onbarding.OnboardingScreen2
import com.example.bloom.ui.screens.onbarding.OnboardingScreen3
import com.example.bloom.ui.screens.onbarding.OnboardingScreen4
import com.example.bloom.ui.theme.Fraunces
import com.example.bloom.util.AppOpenAdManager
import com.example.bloom.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    appOpenAdManager: AppOpenAdManager
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                viewModel = viewModel,
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding1.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Onboarding1.route) {
            OnboardingScreen1(
                onGetStarted = {
                    navController.navigate(Screen.Onboarding2.route)
                }
            )
        }
        composable(Screen.Onboarding2.route) {
            OnboardingScreen2(
                onBack = {
                    navController.popBackStack()
                },
                onContinue = { name ->
                    viewModel.setUserName(name)
                    navController.navigate(Screen.Onboarding3.route)
                },
                serifFont = Fraunces
            )
        }
        composable(Screen.Onboarding3.route) {
            OnboardingScreen3(
                onBack = {
                    navController.popBackStack()
                },
                onContinue = { goals ->
                    navController.navigate(Screen.Onboarding4.route)
                },
                serifFont = Fraunces
            )
        }
        composable(Screen.Onboarding4.route) {
            OnboardingScreen4(
                onBack = {
                    navController.popBackStack()
                },
                onEnterBloom = { time ->
                    viewModel.setOnboardingCompleted(true)
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding1.route) { inclusive = true }
                    }
                },
                onSetLater = {
                    viewModel.setOnboardingCompleted(true)
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding1.route) { inclusive = true }
                    }
                },
                serifFont = Fraunces
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                appOpenAdManager = appOpenAdManager
            )
        }
    }
}

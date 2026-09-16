package com.example.bloom.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding1 : Screen("onboarding1")
    data object Onboarding2 : Screen("onboarding2")
    data object Onboarding3 : Screen("onboarding3")
    data object Onboarding4 : Screen("onboarding4")
    data object Home : Screen("home")
}

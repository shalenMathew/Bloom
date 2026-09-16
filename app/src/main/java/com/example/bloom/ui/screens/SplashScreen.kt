package com.example.bloom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.bloom.R
import com.example.bloom.util.AppOpenAdManager
import com.example.bloom.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: MainViewModel,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState()

    LaunchedEffect(isOnboardingCompleted) {
        if (isOnboardingCompleted != null) {
            delay(2000)
            if (isOnboardingCompleted == true) {
                onNavigateToHome()
            } else {
                onNavigateToOnboarding()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splash_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

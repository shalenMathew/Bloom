package com.example.bloom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.bloom.ui.navigation.NavGraph
import com.example.bloom.ui.theme.BloomTheme
import com.example.bloom.util.AppOpenAdManager
import com.example.bloom.viewmodel.MainViewModel
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Bloom)
        enableEdgeToEdge()

        MobileAds.initialize(this) {}
        appOpenAdManager.loadAd(this)

        setContent {
            val viewModel: MainViewModel = hiltViewModel()

            BloomTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavGraph(
                            navController = navController,
                            viewModel = viewModel,
                            appOpenAdManager = appOpenAdManager
                        )
                    }
                }
            }
        }
    }
}

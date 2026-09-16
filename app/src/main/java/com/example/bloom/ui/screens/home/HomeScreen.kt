package com.example.bloom.ui.screens.home

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloom.ui.components.BannerAdView
import com.example.bloom.ui.screens.bottomBar.BloomBottomBar
import com.example.bloom.ui.screens.home.util.HomeGoalSection
import com.example.bloom.ui.screens.home.util.HomeHeader
import com.example.bloom.ui.screens.home.util.PcosFaqSection
import com.example.bloom.ui.screens.home.util.TodaysGoalsCard
import com.example.bloom.ui.screens.home.util.TodaysTip
import com.example.bloom.ui.theme.Fraunces
import com.example.bloom.util.AppOpenAdManager
import com.example.bloom.viewmodel.MainViewModel
import kotlinx.coroutines.delay

private val BloomBackground = Color(0xFFFCFAF8)

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    appOpenAdManager: AppOpenAdManager,
    serifFont: FontFamily = Fraunces
) {
    val scrollState = rememberScrollState()
    var selectedTab by remember { mutableIntStateOf(0) }
    val name by viewModel.userName.collectAsState()
    val shouldShowInterlude by viewModel.shouldShowAdInterlude.collectAsState()
    val isAdReady by appOpenAdManager.isAdReady.collectAsState()
    val context = LocalContext.current
    var countdown by remember { mutableIntStateOf(4) }

    LaunchedEffect(shouldShowInterlude) {
        if (shouldShowInterlude) {
            while (countdown > 0) {
                if (isAdReady) {
                    val activity = context as? Activity
                    if (activity != null) {
                        appOpenAdManager.showAdIfAvailable(activity) {
                            viewModel.dismissAdInterlude()
                        }
                    } else {
                        viewModel.dismissAdInterlude()
                    }
                    return@LaunchedEffect
                }
                delay(1000)
                countdown--
            }
            if (!isAdReady) {
                Toast.makeText(context, "Failed loading ads", Toast.LENGTH_SHORT).show()
                viewModel.dismissAdInterlude()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                BloomBottomBar(
                    selectedItem = selectedTab,
                    onItemSelected = { selectedTab = it }
                )
            },
            containerColor = BloomBackground
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
            ) {
                HomeHeader(
                    name = name,
                    serifFont = serifFont
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(4.dp))

                    TodaysGoalsCard(
                        serifFont = serifFont,
                    )

                    BannerAdView()

                    PcosFaqSection(serifFont)

                    Spacer(modifier = Modifier.height(24.dp))

                    HomeGoalSection(serifFont)

                    Spacer(modifier = Modifier.height(24.dp))

                    TodaysTip(serifFont)

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

        if (shouldShowInterlude) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Showing ads in $countdown...",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

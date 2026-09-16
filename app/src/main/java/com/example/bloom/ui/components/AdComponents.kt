package com.example.bloom.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.bloom.util.AdConfig
import com.google.android.gms.ads.*

@Composable
fun BannerAdView(
    modifier: Modifier = Modifier,
    paddingDp: Int = 32 // Default for 16dp on each side
) {
    val context = LocalContext.current
    var isAdVisible by remember { mutableStateOf(false) }

    val adWidth = context.resources.displayMetrics.widthPixels
    val density = context.resources.displayMetrics.density
    val adWidthDp = (adWidth / density).toInt() - paddingDp

    if (isAdVisible) {
        Spacer(modifier = Modifier.height(24.dp))
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(if (isAdVisible) Dp.Unspecified else 0.dp),
        factory = { ctx ->
            AdView(ctx).apply {
                setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(ctx, adWidthDp))
                adUnitId = AdConfig.BANNER_AD_UNIT_ID
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        isAdVisible = true
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        isAdVisible = false
                    }
                }
                loadAd(AdRequest.Builder().build())
            }
        }
    )

    if (isAdVisible) {
        Spacer(modifier = Modifier.height(24.dp))
    }
}

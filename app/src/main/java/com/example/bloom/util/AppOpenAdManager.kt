package com.example.bloom.util

import android.app.Activity
import android.content.Context
import com.example.bloom.data.PreferenceManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppOpenAdManager @Inject constructor(
    private val preferenceManager: PreferenceManager,
) {
    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    private var isShowingAd = false
    private var loadTime: Long = 0

    private val _isAdReady = MutableStateFlow(false)
    val isAdReady: StateFlow<Boolean> = _isAdReady.asStateFlow()

    fun loadAd(context: Context) {
        if (isLoadingAd || isAdAvailable()) {
            if (isAdAvailable()) _isAdReady.value = true
            return
        }

        isLoadingAd = true
        _isAdReady.value = false
        val request = AdRequest.Builder().build()
        AppOpenAd.load(
            context,
            AdConfig.APP_OPEN_AD_UNIT_ID,
            request,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingAd = false
                    loadTime = Date().time
                    _isAdReady.value = true
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoadingAd = false
                    _isAdReady.value = false
                }
            }
        )
    }

    fun isAdAvailable(): Boolean {
        return (appOpenAd != null) && wasLoadTimeLessThanNHoursAgo(4)
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference: Long = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < (numMilliSecondsPerHour * numHours)
    }

    fun showAdIfAvailable(activity: Activity, onAdDismissed: (() -> Unit)? = null) {
        if (isShowingAd) return

        if (!isAdAvailable()) {
            loadAd(activity)
            onAdDismissed?.invoke()
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            val isOnboardingCompleted = preferenceManager.isOnboardingCompleted.first()
            if (!isOnboardingCompleted) {
                onAdDismissed?.invoke()
                return@launch
            }

            appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    appOpenAd = null
                    isShowingAd = false
                    _isAdReady.value = false
                    loadAd(activity)
                    onAdDismissed?.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    appOpenAd = null
                    isShowingAd = false
                    _isAdReady.value = false
                    loadAd(activity)
                    onAdDismissed?.invoke()
                }

                override fun onAdShowedFullScreenContent() {
                    isShowingAd = true
                }
            }
            appOpenAd?.show(activity)
        }
    }
}

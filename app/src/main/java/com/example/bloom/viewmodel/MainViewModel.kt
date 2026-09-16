package com.example.bloom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloom.data.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _isOnboardingCompleted = MutableStateFlow<Boolean?>(null)
    val isOnboardingCompleted: StateFlow<Boolean?> = _isOnboardingCompleted.asStateFlow()

    private val _userName = MutableStateFlow("Sofia")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _shouldShowAdInterlude = MutableStateFlow(false)
    val shouldShowAdInterlude: StateFlow<Boolean> = _shouldShowAdInterlude.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceManager.isOnboardingCompleted.collectLatest { completed ->
                _isOnboardingCompleted.value = completed
            }
        }
        viewModelScope.launch {
            preferenceManager.userName.collectLatest { name ->
                _userName.value = name
            }
        }
        
        // Only allow ad interlude on cold starts for returning users
        viewModelScope.launch {
            val wasAlreadyCompleted = preferenceManager.isOnboardingCompleted.first()
            if (wasAlreadyCompleted) {
                _shouldShowAdInterlude.value = true
            }
        }
    }

    fun setOnboardingCompleted(completed: Boolean) {
        viewModelScope.launch {
            preferenceManager.setOnboardingCompleted(completed)
        }
    }

    fun setUserName(name: String) {
        viewModelScope.launch {
            preferenceManager.setUserName(name)
        }
    }

    fun dismissAdInterlude() {
        _shouldShowAdInterlude.value = false
    }
}

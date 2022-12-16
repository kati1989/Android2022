package com.treetrack.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentSplashViewModel : ViewModel() {
    val goToLogin = MutableLiveData(false)

    init {
        viewModelScope.launch {
            delay(2000L)
            goToLogin.value = true
        }
    }
}
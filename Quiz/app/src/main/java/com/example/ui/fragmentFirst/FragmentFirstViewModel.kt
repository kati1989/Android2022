package com.example.ui.fragmentFirst

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentFirstViewModel : ViewModel() {

    val name =  MutableLiveData("")
}
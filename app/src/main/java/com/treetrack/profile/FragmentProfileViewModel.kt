package com.treetrack.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentProfileViewModel : ViewModel() {
    val profileUser = MutableLiveData<User>()
}
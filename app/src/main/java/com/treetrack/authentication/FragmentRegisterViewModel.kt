package com.treetrack.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentRegisterViewModel : ViewModel() {
    val firstname = MutableLiveData("")
    val lastname = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val type = MutableLiveData(2)
    val departmentId = MutableLiveData(2)
    val location = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val mentorId = MutableLiveData(0)


}
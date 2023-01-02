package com.treetrack.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentUpdateProfileViewModel : ViewModel() {

    val lastName = MutableLiveData("Kovacs")
    val firstName = MutableLiveData("Katalin")
    val location = MutableLiveData("Targu Mures2")
    val phoneNumber = MutableLiveData("0766666666")
    val imageUrl =
        MutableLiveData("https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png")
}
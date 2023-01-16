package com.treetrack.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentAddTaskViewModel : ViewModel() {
    val title = MutableLiveData("Test Title")
    val description = MutableLiveData("Test Description")
    val priority = MutableLiveData("1")
    val deadline =
        MutableLiveData("1681202977183") // Mon Apr 10 2023 22:49:37 GMT-1000 -> https://codechi.com/dev-tools/date-to-millisecond-calculators/
    val departmentId = MutableLiveData("2")
    val status = MutableLiveData("0")
}
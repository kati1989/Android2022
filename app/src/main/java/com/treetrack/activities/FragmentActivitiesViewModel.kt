package com.treetrack.activities

import androidx.lifecycle.ViewModel
import com.treetrack.api.data.activities.ActivitiesResponseItem

class FragmentActivitiesViewModel : ViewModel() {
    val activities = ArrayList<ActivitiesResponseItem>()
    val activitiesAdapter = ActivitiesAdapter(activities)
}
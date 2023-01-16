package com.treetrack.activities

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.treetrack.ActivitySubType
import com.treetrack.ActivityType

@BindingAdapter("android:setTypeOfActivity")
fun setTypeOfActivity(textView: TextView, type: Int) {
    textView.text = ActivityType.fromInt(type).toString()
}

@BindingAdapter("android:setSubTypeOfActivity")
fun setSubTypeOfActivity(textView: TextView, subType: Int) {
    textView.text = ActivitySubType.fromInt(subType).toString()
}
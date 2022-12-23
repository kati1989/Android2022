package com.treetrack.tasks

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("android:setTextDate")
fun setDateFromMillis(textView: TextView, millis: Long) {
    textView.text = convertMillisToDate(millis)
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToDate(dateInMilliseconds: Long): String? {
    // define once somewhere in order to reuse it
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // JVM representation of a millisecond epoch absolute instant
    val instant = Instant.ofEpochMilli(dateInMilliseconds)

    // Adding the timezone information to be able to format it (change accordingly)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date) // ex: 10/12/2019 06:35:45
}
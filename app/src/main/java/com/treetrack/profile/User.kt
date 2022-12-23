package com.treetrack.profile

data class User(
    val ID: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val location: String?,
    val phone_number: String?,
    val image: String?,
)
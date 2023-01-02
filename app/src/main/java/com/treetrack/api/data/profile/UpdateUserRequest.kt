package com.treetrack.api.data.profile

data class UpdateUserRequest(
    val firstName: String,
    val imageUrl: String,
    val lastName: String,
    val location: String,
    val phoneNumber: String
)
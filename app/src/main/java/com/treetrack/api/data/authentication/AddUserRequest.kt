package com.treetrack.api.data.authentication

data class AddUserRequest(
    val departmentId: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val location: String,
    val mentorId: Int,
    val passwordKey: String,
    val phoneNumber: String,
    val type: Int
)
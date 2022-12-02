package com.treetrack.api.data.authentication

data class LoginResponse(
    val deadline: Long,
    val token: String,
    val userId: Int
)
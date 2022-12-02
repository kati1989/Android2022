package com.treetrack.api.data.authentication

data class LoginRequest(
    val passwordKey: String,
    val email: String
)

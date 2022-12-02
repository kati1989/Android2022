package com.treetrack.api

import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import retrofit2.Response

object ApiRepository {

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return ApiClient.apiClient.login(loginRequest)
    }
}
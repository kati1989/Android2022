package com.treetrack.api

import com.treetrack.api.data.activities.ActivitiesResponse
import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import com.treetrack.api.data.profile.UserResponse
import retrofit2.Response

object ApiRepository {

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return ApiClient.apiClient.login(loginRequest)
    }

    suspend fun user(token: String): Response<UserResponse> {
        return ApiClient.apiClient.user(token)
    }

    suspend fun getActivities(token: String): Response<ActivitiesResponse> {
        return ApiClient.apiClient.getActivities(token)
    }
}
package com.treetrack.api

import com.treetrack.api.data.activities.ActivitiesResponseItem
import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import com.treetrack.api.data.profile.UpdateUserRequest
import com.treetrack.api.data.profile.UpdateUserResponse
import com.treetrack.api.data.profile.UserResponse
import com.treetrack.api.data.tasks.TaskRequest
import com.treetrack.api.data.tasks.TaskResponse
import com.treetrack.api.data.tasks.TasksResponseItem
import retrofit2.Response

object ApiRepository {

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return ApiClient.apiClient.login(loginRequest)
    }

    suspend fun user(token: String): Response<UserResponse> {
        return ApiClient.apiClient.user(token)
    }

    suspend fun updateUserData(
        token: String,
        updateUserRequest: UpdateUserRequest
    ): Response<UpdateUserResponse> {
        return ApiClient.apiClient.updateUser(token, updateUserRequest)
    }

    suspend fun getActivities(token: String): Response<ArrayList<ActivitiesResponseItem>> {
        return ApiClient.apiClient.getActivities(token)
    }

    suspend fun getTasks(token: String): Response<ArrayList<TasksResponseItem>> {
        return ApiClient.apiClient.getTasks(token)
    }

    suspend fun createTask(token: String, taskRequest: TaskRequest): Response<TaskResponse> {
        return ApiClient.apiClient.createTask(token, taskRequest)
    }
}
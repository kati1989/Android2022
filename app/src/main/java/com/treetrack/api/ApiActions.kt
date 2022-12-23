package com.treetrack.api

import com.treetrack.api.data.activities.ActivitiesResponse
import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import com.treetrack.api.data.profile.UserResponse
import com.treetrack.api.data.tasks.TaskRequest
import com.treetrack.api.data.tasks.TaskResponse
import com.treetrack.api.data.tasks.TasksResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiActions {

    //retrofit
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/user")
    suspend fun user(@Header("token") token: String): Response<UserResponse>

    @GET("/activity/getActivities")
    suspend fun getActivities(@Header("token") token: String): Response<ActivitiesResponse>

    @GET("/task/getTasks")
    suspend fun getTasks(@Header("token") token: String): Response<ArrayList<TasksResponseItem>>

    @POST("/task/create")
    suspend fun createTask(
        @Header("token") token: String,
        @Body taskRequest: TaskRequest
    ): Response<TaskResponse>
}
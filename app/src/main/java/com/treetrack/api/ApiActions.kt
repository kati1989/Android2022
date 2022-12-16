package com.treetrack.api

import com.treetrack.api.data.activities.ActivitiesResponse
import com.treetrack.api.data.authentication.AddUserRequest
import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import com.treetrack.api.data.profile.UserResponse
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
}
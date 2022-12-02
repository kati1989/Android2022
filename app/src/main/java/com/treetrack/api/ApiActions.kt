package com.treetrack.api

import com.treetrack.api.data.authentication.AddUserRequest
import com.treetrack.api.data.authentication.LoginRequest
import com.treetrack.api.data.authentication.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiActions {

    //retrofit
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

//    @POST("/users/addUser")
//    suspend fun register(@Body addUser: AddUserRequest): Response<AddUserResponse>

}
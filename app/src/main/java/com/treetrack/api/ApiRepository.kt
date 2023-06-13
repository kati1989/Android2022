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
import okhttp3.ResponseBody
import retrofit2.Response
import java.time.Instant

object ApiRepository {

    var userResponse = UserResponse(
        ID = 1,
        department_id = 2,
        email = "kovacs.katalin@student.ms.sapientia.ro",
        first_name = "Kovacs",
        last_name = "Katalin",
        location = "Marosvasarhely",
        phone_number = "123456789",
        type = 1,
        image = "https://example.com/image.jpg"
    )

    val activity1 = ActivitiesResponseItem(
        ID = 1,
        created_by_user_id = 1,
        created_time = System.currentTimeMillis(),
        note = "Első tevékenység",
        progress = "In progress",
        sub_ID = 10,
        sub_type = 1,
        sub_user_ID = 20,
        type = 1
    )

    val activity2 = ActivitiesResponseItem(
        ID = 2,
        created_by_user_id = 2,
        created_time = System.currentTimeMillis(),
        note = "Második tevékenység",
        progress = "Completed",
        sub_ID = 11,
        sub_type = 2,
        sub_user_ID = 21,
        type = 2
    )

    val activity3 = ActivitiesResponseItem(
        ID = 3,
        created_by_user_id = 3,
        created_time = System.currentTimeMillis(),
        note = "Harmadik tevékenység",
        progress = "In progress",
        sub_ID = 12,
        sub_type = 3,
        sub_user_ID = 22,
        type = 3
    )

    val task1 = TasksResponseItem(
        ID = 1,
        asigned_to_user_ID = 1,
        created_by_user_ID = 2,
        created_time = System.currentTimeMillis(),
        deadline = System.currentTimeMillis() + 86400000, // Példa: 1 nap hozzáadása a jelenlegi időponthoz
        department_ID = 1,
        description = "Első feladat",
        priority = 1,
        progress = "In progress",
        status = 1,
        title = "Feladat 1"
    )

    val task2 = TasksResponseItem(
        ID = 2,
        asigned_to_user_ID = 2,
        created_by_user_ID = 1,
        created_time = System.currentTimeMillis(),
        deadline = System.currentTimeMillis() + 172800000, // Példa: 2 nap hozzáadása a jelenlegi időponthoz
        department_ID = 2,
        description = "Második feladat",
        priority = 2,
        progress = "In progress",
        status = 1,
        title = "Feladat 2"
    )

    val task3 = TasksResponseItem(
        ID = 3,
        asigned_to_user_ID = 1,
        created_by_user_ID = 3,
        created_time = System.currentTimeMillis(),
        deadline = System.currentTimeMillis() + 259200000, // Példa: 3 nap hozzáadása a jelenlegi időponthoz
        department_ID = 1,
        description = "Harmadik feladat",
        priority = 3,
        progress = "In progress",
        status = 1,
        title = "Feladat 3"
    )

    var tasksList = arrayListOf<TasksResponseItem>().apply {
        add(task1)

        add(task2)

        add(task3)
    }

    var taskID = 4;
    var activitiesList = ArrayList<ActivitiesResponseItem>().apply {
        add(activity1)
        add(activity2)
        add(activity3)
    }

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        var response: Response<LoginResponse>;

        if (loginRequest.email == "kovacs.katalin@student.ms.sapientia.ro"
            && loginRequest.passwordKey == "3tracker_student16"
        ) {
            val loginResponse = LoginResponse(deadline = 123456789, token = "token", userId = 1)
            response = Response.success(loginResponse)
        } else {

            val errorCode = 404
            val errorResponseBody: ResponseBody? = null
            response = Response.error(errorCode, errorResponseBody);
        }
        return response;
    }

    suspend fun user(token: String): Response<UserResponse> {
        val response: Response<UserResponse> = Response.success(userResponse)
        return response;
    }

    suspend fun updateUserData(
        token: String,
        updateUserRequest: UpdateUserRequest
    ): Response<UpdateUserResponse> {
        userResponse.first_name = updateUserRequest.firstName;
        userResponse.last_name = updateUserRequest.lastName;
        userResponse.image = updateUserRequest.imageUrl;
        userResponse.location = updateUserRequest.location
        userResponse.phone_number = updateUserRequest.phoneNumber

        val message = "Felhasználó sikeresen frissítve"
        val updateUserResponse = UpdateUserResponse(message)

        return Response.success(updateUserResponse)
    }

    suspend fun getActivities(token: String): Response<ArrayList<ActivitiesResponseItem>> {
        val response: Response<ArrayList<ActivitiesResponseItem>> = Response.success(activitiesList)
        return response;
    }

    suspend fun getTasks(token: String): Response<ArrayList<TasksResponseItem>> {
        val response: Response<ArrayList<TasksResponseItem>> = Response.success(tasksList)
        return response
    }

    suspend fun createTask(token: String, taskRequest: TaskRequest): Response<TaskResponse> {
        tasksList.add(
            TasksResponseItem(
                taskID,
                taskRequest.assignedToUserId,
                userResponse.ID,
                System.currentTimeMillis(),
                taskRequest.deadline,
                taskRequest.departmentId,
                taskRequest.description,
                taskRequest.priority,
                null,
                taskRequest.status,
                taskRequest.title
            )
        )
        taskID++

        val message = "Task lista sikeresen frissítve"
        val createTask = TaskResponse(message)
        return Response.success(createTask)
    }
}
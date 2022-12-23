package com.treetrack.api.data.tasks

data class TasksResponseItem(
    val ID: Int,
    val asigned_to_user_ID: Int,
    val created_by_user_ID: Int,
    val created_time: Long,
    val deadline: Long,
    val department_ID: Int,
    val description: String,
    val priority: Int,
    val progress: String?,
    val status: Int,
    val title: String
)
package com.treetrack.api.data.tasks

data class TaskRequest(
    val title: String,
    val assignedToUserId: Int = 24,
    val deadline: Long,
    val departmentId: Int,
    val description: String,
    val priority: Int,
    val status: Int
)
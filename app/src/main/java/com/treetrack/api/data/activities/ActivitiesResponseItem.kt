package com.treetrack.api.data.activities

data class ActivitiesResponseItem(
    val ID: Int,
    val created_by_user_id: Int,
    val created_time: Long,
    val note: String?,
    val progress: String?,
    val sub_ID: Int,
    val sub_type: Int,
    val sub_user_ID: Int,
    val type: Int
)
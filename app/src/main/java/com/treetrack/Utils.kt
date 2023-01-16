package com.treetrack

enum class ActivityType(val value: Int) {
    DEPARTMENT(1),
    TASK(2),
    ANNOUNCEMENT(3);

    companion object {
        fun fromInt(value: Int): ActivityType? {
            return values().find { it.value == value }
        }
    }
}

enum class ActivitySubType(val value: Int) {
    DEPARTMENT_USER_ADDED(1),
    TASK_CREATED(2),
    TASK_ASSIGNED(3),
    TASK_STATUS_CHANGE(4),
    TASK_PROGRESS_CHANGE(5);

    companion object {
        fun fromInt(value: Int): ActivitySubType? {
            return values().find { it.value == value }
        }
    }
}

enum class UserType(val value: Int) {
    HR_MANAGER(0),
    DEPARTMENT_LEAD(1),
    SIMPLE_EMPLOYEE(2);

    companion object {
        fun fromInt(value: Int): UserType? {
            return values().find { it.value == value }
        }
    }
}

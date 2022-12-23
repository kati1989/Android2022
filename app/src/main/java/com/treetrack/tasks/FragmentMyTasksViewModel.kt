package com.treetrack.tasks

import androidx.lifecycle.ViewModel
import com.treetrack.api.data.tasks.TasksAdapter
import com.treetrack.api.data.tasks.TasksResponseItem

class FragmentMyTasksViewModel : ViewModel() {
    val tasks = ArrayList<TasksResponseItem>()
    val tasksAdapter = TasksAdapter(tasks = tasks)
}
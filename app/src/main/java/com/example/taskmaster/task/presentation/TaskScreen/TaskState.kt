package com.example.taskmaster.task.presentation.TaskScreen

import com.example.taskmaster.task.domain.model.TaskUi

data class TaskState (
    val isLoading : Boolean = true,
    val TaskList:List<TaskUi> = emptyList(),


) // will hold class


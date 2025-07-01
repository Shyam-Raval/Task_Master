package com.example.taskmaster.task.presentation.TaskScreen

import com.example.taskmaster.task.domain.model.TaskUi

sealed class TaskEvents {
    // save to do
    data class SaveTask(val taskUi:TaskUi) : TaskEvents()

    data class UpdateTodo(val taskUi:TaskUi):TaskEvents()
    data class DeleteTodo(val id:String):TaskEvents()
}
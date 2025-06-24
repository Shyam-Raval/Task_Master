package com.example.taskmaster.task.domain.repository

import com.example.taskmaster.task.data.TaskDTO
import com.example.taskmaster.task.domain.model.TaskUi
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun saveTask(taskUi: TaskUi)
    suspend fun getTask(): Flow<List<TaskUi>>

}
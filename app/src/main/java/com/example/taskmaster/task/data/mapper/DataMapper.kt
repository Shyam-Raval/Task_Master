package com.example.taskmaster.task.data.mapper

import com.example.taskmaster.task.data.TaskDTO
import com.example.taskmaster.task.domain.model.TaskUi
import java.util.UUID

fun TaskUi.toTaskDTO(): TaskDTO{
    return TaskDTO(
        id = id ?:  UUID.randomUUID().toString(),
        title = title,
        description = description,
        priority = priority,
        dateAdded = System.currentTimeMillis()
    )

}


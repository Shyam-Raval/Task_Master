package com.example.taskmaster.task.domain.mapper

import com.example.taskmaster.core.formatTimestampToDateTime
import com.example.taskmaster.task.data.TaskDTO
import com.example.taskmaster.task.domain.model.TaskUi


fun TaskDTO.toTaskUI():TaskUi{
    return TaskUi(
        id =id!!,
        title = title,
        description = description,
        priority = priority,
        dateAdded = formatTimestampToDateTime(dateAdded!!)
    )
}
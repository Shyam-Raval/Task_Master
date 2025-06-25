package com.example.taskmaster.task.domain.model

import com.example.taskmaster.core.utils.Priority

data class TaskUi(
    val id:String?=null,
    val title:String,
    val description : String = "", //empty string as it is optional
    val priority: Priority = Priority.LOW ,//defulat low hoga
    val dateAdded : String?=null
)
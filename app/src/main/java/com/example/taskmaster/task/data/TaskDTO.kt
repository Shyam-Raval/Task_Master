package com.example.taskmaster.task.data

import com.example.taskmaster.core.utils.Priority

data class TaskDTO (
    val id:String?=null,
    val title:String = "",
    val description : String = "", //empty string as it is optional
    val priority: Priority = Priority.LOW ,//defulat low hoga
    val dateAdded : Long ?= null
)


// now to map we will create a fn in data mapper

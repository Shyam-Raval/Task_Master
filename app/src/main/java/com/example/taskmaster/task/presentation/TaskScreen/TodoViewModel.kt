package com.example.taskmaster.task.presentation.TaskScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.task.data.repository.TaskRepositoryImplementation
import com.example.taskmaster.task.domain.model.TaskUi
import com.example.taskmaster.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel:ViewModel() {

    //used to hold data of ui

    //rotate krte haim device to phir aviticty is destoryed
    //data same rehga if we use viewmodel

  private  val taskRepository: TaskRepository = TaskRepositoryImplementation()

   private val _state = MutableStateFlow(TaskState()) // events will modify
    val state = _state.asStateFlow() //immutable cannot be modifier used screen
    init{
        getTask()
    }
    fun onEvent(events:TaskEvents){
       when(events){
           is TaskEvents.SaveTask->{
               saveTask(events.taskUi)
           }
       }
    }
   private fun saveTask(taskui: TaskUi){
        viewModelScope.launch {
            taskRepository.saveTask(taskui)
        }


    }
    private fun getTask(){
        Log.d("Task", "getTask() called")
        viewModelScope.launch {
              // Check if function is called
            Log.d("Task", "Coroutine started")  // Check if coroutine starts

            try {
                taskRepository.getTask().collect { taskList ->
                    Log.d("Task", taskList.toString())
                    _state.value = state.value.copy(
                        TaskList = taskList,
                        isLoading = false
                    )

                }
            }catch (e: Exception) {
                Log.e("Task", "Error in flow collection", e)
            }

        }
    }
}
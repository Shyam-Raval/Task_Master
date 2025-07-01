package com.example.taskmaster.task.data.repository

import com.example.taskmaster.task.data.TaskDTO
import com.example.taskmaster.task.data.mapper.toTaskDTO
import com.example.taskmaster.task.domain.mapper.toTaskUI
import com.example.taskmaster.task.domain.model.TaskUi
import com.example.taskmaster.task.domain.repository.TaskRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await



class TaskRepositoryImplementation : TaskRepository {
    //instance of firebase
    private val firebase = FirebaseDatabase.getInstance()
    private val toDoRef = firebase.getReference("ToDoItems")


    override suspend fun saveTask(taskUi: TaskUi) {
        val taskDTO = taskUi.toTaskDTO()

        try{
            toDoRef.child(taskDTO.id!!).setValue(taskDTO).await() // .setvalue data ko save krta hain
        }
        catch (e : Exception){

        }
    }

    override suspend fun getTask(): Flow<List<TaskUi>> = callbackFlow{
       // we add event listener
        val Listener = object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val TaskUiItems:List<TaskUi> =  snapshot.children.mapNotNull {
                    it.getValue(TaskDTO::class.java)
                }.map {
                    it.toTaskUI()
                }
                trySend(TaskUiItems)
            }

            override fun onCancelled(error: DatabaseError) {
                close()
            }
        }

        toDoRef.addValueEventListener(
            Listener

        )
        awaitClose{
            toDoRef.removeEventListener(Listener)
        }
    }

    override suspend fun updateToDo(taskUi: TaskUi) {
        val taskDTO = taskUi.toTaskDTO()
       try{ toDoRef.child(taskUi.id!!).setValue(taskDTO).await()}
       catch(e:Exception){

       }
    }

    override suspend fun deleteTodo(id: String) {
        try{
            toDoRef.child(id).removeValue().await()
        }
        catch (e:Exception){}
    }


}
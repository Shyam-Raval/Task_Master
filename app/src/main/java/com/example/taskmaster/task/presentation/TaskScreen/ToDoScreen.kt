package com.example.taskmaster.task.presentation.TaskScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmaster.R
import com.example.taskmaster.core.component.TaskDialog
import com.example.taskmaster.core.utils.Priority
import com.example.taskmaster.task.domain.model.TaskUi
import com.example.taskmaster.task.presentation.components.TaskItem
import kotlinx.coroutines.launch

@Composable
fun ToDoScreen(
    state : TaskState,
    events: (TaskEvents)->Unit,
) {

    val scope = rememberCoroutineScope()
    val showTaskDialog = rememberSaveable { mutableStateOf(false) }
    var isEditMode by rememberSaveable { mutableStateOf(false ) }
    var selectedTitle by rememberSaveable { mutableStateOf("") }
    var selectedDescription by rememberSaveable { mutableStateOf("") }
    var selectedPriority by rememberSaveable { mutableStateOf(Priority.LOW) }
    var selectedId by rememberSaveable { mutableStateOf("") }


    Box(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.light_black)),
        contentAlignment = Alignment.Center

    ){
        if(state.TaskList.isEmpty() && !state.isLoading){
            Text(text = "Add Tasks, there are no Tasks!" , color = Color.Cyan)

        }
       else if(state.isLoading ){
           CircularProgressIndicator()
       }

       else if(state.TaskList.isNotEmpty()){

           LazyColumn(
               modifier = Modifier.fillMaxSize(),
               contentPadding = PaddingValues(8.dp),
               verticalArrangement = Arrangement.spacedBy(8.dp)
           ) {
               items(state.TaskList , key= {
                   it.id!! //we want ki recomposition km ho ga isse
               }){currentTaskUiItem->
                   TaskItem(taskUi =currentTaskUiItem){

                       selectedId = currentTaskUiItem.id!!
                       selectedTitle =currentTaskUiItem.title
                       selectedDescription =currentTaskUiItem.description
                       selectedPriority = currentTaskUiItem.priority
                       isEditMode = true
                       showTaskDialog.value = true
                   }
               }

           }

       }


        FloatingActionButton(
            modifier = Modifier.padding(20.dp).align(Alignment.BottomEnd),
            onClick = {
                showTaskDialog.value = true
                isEditMode = false

            },
            containerColor = colorResource(R.color.purple_200)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )

        }
        if(showTaskDialog.value){
            TaskDialog(
                isEditMode = isEditMode,
                onDismiss = {
                    showTaskDialog.value = false
                },
                onAddToDo = { title,description,priority,->
                    scope.launch {
                        val taskui  = TaskUi(
                            title = title,
                            description = description,
                             priority = priority

                        )
                        events(TaskEvents.SaveTask(taskui))


                    }
                    showTaskDialog.value = false

                },
                onDeleteToDo = {
                    scope.launch{
                        events(TaskEvents.DeleteTodo(selectedId))
                    }
                    showTaskDialog.value = false
                },
                onUpdateToDo = { title,description,priority,->
                    scope.launch {
                        val taskui  = TaskUi(
                            id = selectedId,
                            title = title,
                            description = description,
                            priority = priority

                        )
                        events(TaskEvents.UpdateTodo(taskui))


                    }
                    showTaskDialog.value = false


                },
                existingPriority = selectedPriority,
                existingTitle =  selectedTitle,
                existingDescription = selectedDescription,


            )
        }
    }
}


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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmaster.R
import com.example.taskmaster.core.utils.Priority
import com.example.taskmaster.task.domain.model.TaskUi
import com.example.taskmaster.task.presentation.components.TaskItem
import kotlinx.coroutines.launch

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun ToDoScreen(
    state : TaskState,
    events: (TaskEvents)->Unit,
) {

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.light_black)),
        contentAlignment = Alignment.Center

    ){
       if(state.isLoading){
           CircularProgressIndicator(

           )
       }

       if(state.TaskList.isNotEmpty()){

           LazyColumn(
               modifier = Modifier.fillMaxSize(),
               contentPadding = PaddingValues(8.dp),
               verticalArrangement = Arrangement.spacedBy(8.dp)
           ) {
               items(state.TaskList , key= {
                   it.id //we want ki recomposition km ho ga isse
               }){
                   TaskItem(taskUi = it)
               }

           }

       }

        FloatingActionButton(
            modifier = Modifier.padding(20.dp).align(Alignment.BottomEnd),
            onClick = {
                scope.launch {

                    //events(TaskEvents.SaveTask())

                }

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
    }
}

val taskList = listOf(
   TaskUi(
            id = "1",
            title = "DSA",
            description = "Padhle bhai esa time kabhi vapas nhi aayga",
            priority = Priority.LOW,
            dateAdded = "19 JUNE, 11:40 PM, 2025"
        )
    ,

        TaskUi(
            id = "2",
            title = "Android App",
            description = "Jetpack Compose project banana hai",
            priority = Priority.MEDIUM,
            dateAdded = "20 JUNE, 9:00 AM, 2025"
        )
    ,

        TaskUi(
            id = "3",
            title = "Badminton",
            description = "Health bhi important hai bhai!",
            priority = Priority.LOW,
            dateAdded = "20 JUNE, 6:30 PM, 2025"
        )
    ,
    TaskUi(
            id = "4",
            title = "OS Notes",
            description = "Slides padh le exam aa gaya",
            priority = Priority.HIGH,
            dateAdded = "21 JUNE, 10:00 AM, 2025"
        )

)

package com.example.taskmaster.task.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmaster.R
import com.example.taskmaster.core.utils.Priority
import com.example.taskmaster.task.domain.model.TaskUi

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun TaskItemPreview() {
    TaskItem(
        taskUi = TaskUi(
            id = "1", // Added an ID for consistency with typical data models
            title = "DSA",
            description = "Padhle bhai esa time kabhi vapas nhi aayga",
            priority = Priority.HIGH,
            dateAdded = "19 JUNE , 11:40 PM , 2025"
        )
    )
}



// we cant preview as their is no instance

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    taskUi: TaskUi
) {
    val containerColor = when(taskUi.priority){
        Priority.LOW -> colorResource(R.color.Green)
        Priority.MEDIUM -> colorResource(R.color.Yellow)
        Priority.HIGH -> colorResource(R.color.Red)


    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = containerColor)

    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            //title
            Text(
                text = taskUi.title,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(10.dp))
            //description
           if(taskUi.description!=""){
               Text(
                   text = taskUi.description,
                   fontSize = 18.sp,

                   color = Color.White
               )

               //date
               Spacer(Modifier.height(10.dp))

           }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = taskUi.dateAdded!!,
                fontSize = 10.sp,

                color = Color.White,
                textAlign = TextAlign.End
            )

        }
    }

}




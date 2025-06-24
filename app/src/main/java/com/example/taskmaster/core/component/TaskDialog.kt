package com.example.taskmaster.core.component

import android.widget.AutoCompleteTextView.OnDismissListener
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.taskmaster.R
import com.example.taskmaster.core.utils.Priority
import com.example.taskmaster.task.presentation.components.TaskItem
import kotlinx.coroutines.internal.OpDescriptor

@Composable
fun TaskDialog(
    onDismiss:() -> Unit,
    isEditMode: Boolean = true,
    onAddToDo:(String,String,Priority) -> Unit,

    onUpdateToDo:(String,String,Priority) ->Unit,
    onDeleteToDo:()->Unit,
    existingTitle:String ="",
    existingDescription:String = "",
    existingPriority: Priority = Priority.LOW

) {
    Dialog(
        onDismissRequest = {onDismiss()},
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {

            var currentTitle by rememberSaveable { mutableStateOf(if(isEditMode) existingTitle else "") }
            var currentDescription by rememberSaveable { mutableStateOf(if(isEditMode) existingDescription else "") }
            var currentPriority by rememberSaveable { mutableStateOf(if(isEditMode) existingPriority else Priority.LOW) }

            var isTitleEmpty by rememberSaveable { mutableStateOf(false) }
            var enable by rememberSaveable { if(isEditMode) mutableStateOf(false) else mutableStateOf(true) }

            var confirmDeletingToDo by rememberSaveable { mutableStateOf(false) }
            val focusRequester = remember{
                FocusRequester()
            }
            val dialogBackground = when(currentPriority){
                Priority.LOW-> colorResource(R.color.Green)
                Priority.MEDIUM -> colorResource(R.color.Yellow)
                Priority.HIGH -> colorResource(R.color.Red)
            }


            val focusMananger = LocalFocusManager.current
            //add ke time
            //delete ke time
            LaunchedEffect(key1 = enable) {
                focusRequester.requestFocus()
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(color = dialogBackground )
                    .border(2.dp, colorResource(id = R.color.teal_700), RoundedCornerShape(15.dp))
                    .padding(16.dp)
            ) {
                Row(
                    //title aur icon hain is row main
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                    ){
                    Text(
                        text = if(isEditMode) "Edit/Delete Task" else "Task",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontSize = 18.sp
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                //priority
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                    IconWithCircleBackground(
                        selected = currentPriority == Priority.LOW,
                        priority = Priority.LOW
                    ) {
                        currentPriority = Priority.LOW

                    }
                    IconWithCircleBackground(
                        selected = currentPriority == Priority.MEDIUM,
                        priority = Priority.MEDIUM
                    ) {
                        currentPriority = Priority.MEDIUM

                    }
                    IconWithCircleBackground(
                        selected = currentPriority == Priority.HIGH,
                        priority = Priority.HIGH
                    ) {
                        currentPriority = Priority.HIGH

                    }
                }
            }


        }
    }
    
}
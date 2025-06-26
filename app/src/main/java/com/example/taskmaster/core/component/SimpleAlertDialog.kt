package com.example.taskmaster.core.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SimpleAlertDialog(
    title : String,
    text : String,
    onConfirm : () -> Unit,
    onDismiss : () -> Unit,
    onDismissRequest : () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest()},
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }){
                Text(text = "Yes");
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismiss()}) {
                Text("No");
            }
        },
        text = {Text(text)},
        title = {Text(title)}
    )
    
}
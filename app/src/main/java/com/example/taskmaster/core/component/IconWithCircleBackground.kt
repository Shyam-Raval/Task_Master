package com.example.taskmaster.core.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.taskmaster.R
import com.example.taskmaster.core.utils.Priority

@Composable
fun IconWithCircleBackground (
    priority:Priority = Priority.LOW,
    selected : Boolean = false,
    onClick:()->Unit
){
    val iconBackground = when(priority){
        Priority.LOW-> colorResource(R.color.Green)
        Priority.MEDIUM -> colorResource(R.color.Yellow)
        Priority.HIGH -> colorResource(R.color.Red)
    }
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(color = iconBackground, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        if(selected){
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }



}
package com.example.taskmaster.core.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.privacysandbox.ads.adservices.customaudience.CustomAudience

@Composable
fun CustomizedTextField(
    modifier : Modifier,
    text : String,
    label :String,
    onValueChange:(String) -> Unit,
    enabled : Boolean,
    supportingText : String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions:KeyboardActions = KeyboardActions.Default

    ){
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = {onValueChange(it)},
        enabled = enabled,
        label = {Text(text = label)},
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        supportingText = {
            Text(text = supportingText,color = Color.Red , fontSize = 18.sp , fontWeight = FontWeight.Bold )
        }
    )
}
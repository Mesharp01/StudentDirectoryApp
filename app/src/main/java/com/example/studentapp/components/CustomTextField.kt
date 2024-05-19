package com.example.studentapp.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value:  MutableState<TextFieldValue>,
    labelText: String,
    errorCheck: Boolean,
    errorMsg: String) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {value.value = it},
        label = {
            Text(text = labelText)
        },
        placeholder = {
            Text(text = labelText)
        },
        supportingText = {
            if (errorCheck) {
                Text(text = errorMsg)
            }
        },
        isError = errorCheck,
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(value:  String,
                     labelText: String,
                     errorCheck: Boolean,
                     errorMsg: String,
                     onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {onValueChange.invoke(it)},
        label = {
            Text(text = labelText)
        },
        placeholder = {
            Text(text = labelText)
        },
        supportingText = {
            if (errorCheck) {
                Text(text = errorMsg)
            }
        },
        isError = errorCheck,
        modifier = Modifier.fillMaxWidth()
    )
}
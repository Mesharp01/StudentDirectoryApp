package com.example.studentapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studentapp.viewModel.StudentViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.studentapp.R
import com.example.studentapp.components.DefaultTextField
import com.example.studentapp.components.SearchTextField
import com.example.studentapp.components.StudentToolBar
import com.example.studentapp.navigation.NavConstants
import com.example.studentapp.navigation.NavConstants.REMOVE_STUDENT_FROM_SEARCH


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarAndList(studentViewModel: StudentViewModel, navController: NavController, studentUpdate: Boolean? = false) {
    val context = LocalContext.current
    if(studentUpdate == true){
        Toast.makeText(context, stringResource(R.string.update_success_msg), Toast.LENGTH_SHORT).show()
    }
    Scaffold(
        topBar = {
            StudentToolBar(
                onClick = { navController.navigate(NavConstants.MAIN_SCREEN) },
                title = stringResource(id = R.string.search_student)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
            ){
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                val isError by remember {
                    derivedStateOf {
                        var students = studentViewModel.filterStudents(textState.value.text)
                        when{
                            textState.value.text.isEmpty() -> false
                            students.isNotEmpty() -> false
                            else -> {true}
                        }

                    }
                }
                SearchTextField(
                    value = textState,
                    labelText = stringResource(id = R.string.search_student_label),
                    errorCheck = isError,
                    errorMsg = stringResource(id = R.string.search_error_msg)
                )
                LazyColumn() {
                    items(studentViewModel.filterStudents(textState.value.text)) { student ->
                        StudentInfoContent(student, navController, studentViewModel, REMOVE_STUDENT_FROM_SEARCH)
                    }
                }
            }
        })
}
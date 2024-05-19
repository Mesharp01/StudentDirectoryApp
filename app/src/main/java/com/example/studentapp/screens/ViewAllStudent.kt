package com.example.studentapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studentapp.R
import com.example.studentapp.components.DefaultIconButton
import com.example.studentapp.components.PopUp
import com.example.studentapp.viewModel.StudentViewModel
import com.example.studentapp.components.StudentToolBar
import com.example.studentapp.data.ADD_SUCCESS_ARG
import com.example.studentapp.data.EMAIL
import com.example.studentapp.data.EMAIL_ARG_BUTTON
import com.example.studentapp.data.NAME
import com.example.studentapp.data.PHONE_NUM
import com.example.studentapp.data.Student
import com.example.studentapp.navigation.NavConstants
import com.example.studentapp.navigation.NavConstants.REMOVE_STUDENT_FROM_VIEW

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAllStudents(studentViewModel: StudentViewModel, navController: NavController, studentUpdate: Boolean? = false) {
    val context = LocalContext.current
    if(studentUpdate == true){
        Toast.makeText(context, stringResource(R.string.update_success_msg), Toast.LENGTH_SHORT).show()
    }
    Scaffold(
        topBar = {
            StudentToolBar(
                onClick = { navController.navigate(NavConstants.MAIN_SCREEN) },
                title = stringResource(id = R.string.list_student)
            )
        },
        content = {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
            ) {
                items(studentViewModel.listAllStudents()) { student ->
                    StudentInfoContent(student, navController, studentViewModel, navPath = REMOVE_STUDENT_FROM_VIEW)
                }
            }
        })
}

@Composable
fun StudentInfoContent(
    student: Student,
    navController: NavController,
    studentViewModel: StudentViewModel,
    navPath: String
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column()
            {
                Text(
                    text = NAME + "${student.name}",
                    color = Color.Black
                )
                Text(
                    text = EMAIL + "${student.email}",
                    color = Color.Black
                )
                Text(
                    text = PHONE_NUM + "${student.phoneNum}",
                    color = Color.Black
                )
            }
            DefaultIconButton(
                modifier = Modifier.size(24.dp),
                contentDescription = (stringResource(id = R.string.edit_student)),
                tint = Color.Black,
                iconType = Icons.Filled.Edit,
                onClick = {
                    navController.navigate(
                        "${NavConstants.EDIT_STUDENT_PAGE}${EMAIL_ARG_BUTTON}${student.email}"
                    )
                })
            DefaultIconButton(
                modifier = Modifier.size(24.dp),
                contentDescription = (stringResource(id = R.string.remove_student)),
                tint = Color.Black,
                iconType = Icons.Filled.Delete,
                onClick = {
                    openAlertDialog.value = true
                })
            when {
                openAlertDialog.value -> {
                    PopUp(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            openAlertDialog.value = false
                            studentViewModel.removeStudent(student)
                            navController.navigate(navPath)
                        },
                        dialogTitle = stringResource(id = R.string.remove_student),
                        dialogText = stringResource(R.string.pop_up_dialog),
                        icon = Icons.Default.Delete
                    )
                }
            }
        }
        Divider(thickness = 1.dp, color = Color.Black)
    }
}
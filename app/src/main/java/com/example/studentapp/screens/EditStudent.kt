package com.example.studentapp.screens

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studentapp.R
import com.example.studentapp.components.DefaultButton
import com.example.studentapp.components.DefaultTextField
import com.example.studentapp.viewModel.StudentViewModel
import com.example.studentapp.components.StudentToolBar
import com.example.studentapp.data.ADD_SUCCESS_ARG
import com.example.studentapp.data.UPDATE_SUCCESS_ARG
import com.example.studentapp.data.Student
import com.example.studentapp.data.StudentHelper

//reuse add/edit pages based on student availability
//rename to AddEditStudentPage
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditStudentPage(studentEmail: String?, studentViewModel: StudentViewModel, navController: NavController) {
    var student = studentViewModel.findStudent(studentEmail)
        var name = remember { mutableStateOf(student?.name ?: "") } //nullable condition check, if null start with ""

        var isErrorName by remember {
            mutableStateOf(false)
        }

        var email = remember { mutableStateOf(student?.email?: "") }

        var isErrorEmail by remember {
            mutableStateOf(false)
        }

        var phoneNum = remember {
            mutableStateOf(student?.phoneNum?:"")
        }

        var isErrorPhoneNum by remember {
            mutableStateOf(false)
        }

        Scaffold(
            topBar = {
                StudentToolBar(
                    onClick = { navController.popBackStack() },
                    title =
                    if(student == null){
                        stringResource(id = R.string.add_student)
                    }
                    else{stringResource(id = R.string.edit_student)},
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(16.dp)
                ) {
                    Log.d("name error", isErrorName.toString())
                    DefaultTextField(
                        value = name.value,
                        labelText = stringResource(id = R.string.name_input),
                        errorCheck = isErrorName,
                        errorMsg = stringResource(id = R.string.name_error_msg),
                        onValueChange = {
                            name.value = it
                            if (isErrorName) {
                                isErrorName = !StudentHelper.isValidName(it)
                            }
                        }
                    )
                    DefaultTextField(
                        value = email.value,
                        labelText = stringResource(id = R.string.email_input),
                        errorCheck = isErrorEmail,
                        errorMsg = stringResource(id = R.string.email_error_msg),
                        onValueChange = {
                            email.value = it
                            if (isErrorEmail) {
                                isErrorEmail = !StudentHelper.isValidEmail(it)
                            }
                        }
                    )
                    DefaultTextField(
                        value = phoneNum.value,
                        labelText = stringResource(id = R.string.phone_input),
                        errorCheck = isErrorPhoneNum,
                        errorMsg = stringResource(id = R.string.phone_error_msg),
                        onValueChange = {
                            phoneNum.value = it
                            if (isErrorPhoneNum) {
                                isErrorPhoneNum = !StudentHelper.isValidPhoneNum(it)
                            }
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                            DefaultButton(
                                buttonText =
                                if(student == null){
                                    stringResource(id = R.string.add_student_button)
                                }
                                else{stringResource(id = R.string.update_student_button)},
                                onClick = {
                                    if (StudentHelper.isValidStudent(
                                            name.value,
                                            email.value,
                                            phoneNum.value
                                        )
                                    ) {
                                        if(student == null){
                                            val newStudent =
                                                Student(name.value, email.value, phoneNum.value)
                                            studentViewModel.addStudent(newStudent)
                                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                                ADD_SUCCESS_ARG,
                                                true
                                            )
                                            navController.popBackStack()
                                        }
                                        else{
                                        student?.name = name.value
                                        student?.email = email.value
                                        student?.phoneNum = phoneNum.value
                                        navController.previousBackStackEntry?.savedStateHandle?.set(
                                            UPDATE_SUCCESS_ARG,
                                            true
                                        )
                                        navController.popBackStack()
                                        }
                                    } else {
                                        isErrorName = !StudentHelper.isValidName(name.value)
                                        isErrorEmail = !StudentHelper.isValidEmail(email.value)
                                        isErrorPhoneNum =
                                            !StudentHelper.isValidPhoneNum(phoneNum.value)
                                    }
                                })
                        }
                    }
            })
    }
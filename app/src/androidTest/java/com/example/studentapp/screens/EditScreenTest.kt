package com.example.studentapp.screens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.StudentAppTheme
import com.example.studentapp.R
import com.example.studentapp.data.Student
import com.example.studentapp.viewModel.StudentViewModel
import org.junit.Rule
import org.junit.Test

class EditScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun editPhoneNumTest(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                EditStudentPage(studentEmail = "test@test.com", studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextClearance()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextInput("123-123-1234")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.update_student_button)).assertIsDisplayed().performClick()}
    }

    @Test
    fun editEmailTest(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                EditStudentPage(navController = navController, studentViewModel = viewModel, studentEmail = "test@test.com")
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextClearance()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed().performClick().performTextInput("tst@test.com")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.update_student_button)).assertIsDisplayed().performClick()}
    }

    @Test
    fun editNameTest(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                EditStudentPage(navController = navController, studentViewModel = viewModel, studentEmail = "test@test.com")
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed().performClick().performTextClearance()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed().performClick().performTextInput("Editing Test")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.update_student_button)).assertIsDisplayed().performClick()}
    }
}
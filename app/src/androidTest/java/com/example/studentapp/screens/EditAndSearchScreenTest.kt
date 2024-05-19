package com.example.studentapp.screens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.StudentAppTheme
import com.example.studentapp.R
import com.example.studentapp.data.Student
import com.example.studentapp.viewModel.StudentViewModel
import org.junit.Rule
import org.junit.Test

class EditAndSearchScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun editAndSearchScreenTest() {
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                EditAndSearch(studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.search_student_label))
                .assertIsDisplayed().performClick().performTextInput("test@test.com")
        }
        context?.let { composeTestRule.onNodeWithText("Name: Edit Test").assertIsDisplayed() }
        context?.let {
            composeTestRule.onNodeWithContentDescription(it.getString(R.string.edit_student))
                .assertIsDisplayed()
        }
    }

    @Test
    fun editAndSearchScreenTestNoResult() {
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                EditAndSearch(studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed() }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.search_student_label)).assertIsDisplayed().performClick().performTextInput("noStudentFound@test.com") }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.search_error_msg)).assertIsDisplayed() }
    }
}
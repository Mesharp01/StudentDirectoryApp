package com.example.studentapp.screens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.StudentAppTheme
import com.example.studentapp.navigation.NavGraph
import com.example.studentapp.screens.MainScreen
import com.example.studentapp.viewModel.StudentViewModel
import org.junit.Rule
import org.junit.Test
import com.example.studentapp.R

class AddScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun addScreenTestPerfectInput(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                EditStudentPage(null, studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed().performClick().performTextInput("Mallory Sharp")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed().performClick().performTextInput("test@test.com")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextInput("123-123-1233")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student_button)).assertIsDisplayed().performClick()}
    }

    @Test
    fun addScreenTestBadNameInput(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                EditStudentPage(null, studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed().performClick().performTextInput("Mallory!")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed().performClick().performTextInput("test@test.com")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextInput("123-123-1233")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student_button)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_error_msg)).assertIsDisplayed()}
    }

    @Test
    fun addScreenTestBadEmailInput(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                EditStudentPage(null, studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed().performClick().performTextInput("Mallory Sharp")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed().performClick().performTextInput("test@")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextInput("123-123-1233")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student_button)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_error_msg)).assertIsDisplayed()}
    }
    @Test
    fun addScreenTestBadPhoneInput(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                EditStudentPage(null, studentViewModel = viewModel, navController = navController)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed().performClick().performTextInput("Mallory Sharp")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed().performClick().performTextInput("test@test.com")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_input)).assertIsDisplayed().performClick().performTextInput("123-")}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.name_input)).performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student_button)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.phone_error_msg)).assertIsDisplayed()}
    }
}
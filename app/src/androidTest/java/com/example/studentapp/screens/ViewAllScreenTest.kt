package com.example.studentapp.screens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.StudentAppTheme
import com.example.studentapp.R
import com.example.studentapp.data.NAME
import com.example.studentapp.data.Student
import com.example.studentapp.navigation.NavGraph
import com.example.studentapp.screens.ViewAllStudents
import com.example.studentapp.viewModel.StudentViewModel
import org.junit.Rule
import org.junit.Test

class ViewAllScreenTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun viewAllScreenTest() {
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel = StudentViewModel()
                viewModel.addStudent(Student("test1", "test1@123.com", "123-123-1231"))
                ViewAllStudents(navController = navController, studentViewModel = viewModel)
            }
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithContentDescription(it.getString(R.string.remove_student))
                .assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithContentDescription(it.getString(R.string.edit_student))
                .assertIsDisplayed()
        }
    }

    @Test
    fun editStudentButtonTest() {
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                ViewAllStudents(navController = navController, studentViewModel = viewModel)
                EditStudentPage(
                    studentEmail = "test@test.com",
                    studentViewModel = viewModel,
                    navController = navController
                )
            }
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithContentDescription(it.getString(R.string.edit_student))
                .assertIsDisplayed().performClick()
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.name_input)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.email_input)).assertIsDisplayed()
        }
    }

    @Test
    fun removeStudentButtonTestDismiss() {
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                NavGraph(navController = navController, viewModel = viewModel)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed().performClick()}
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithContentDescription(it.getString(R.string.remove_student))
                .assertIsDisplayed().performClick()
        }
        context?.let {
            composeTestRule.onNode(hasTestTag(it.getString(R.string.dialogtitle_test_tag))).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNode(hasTestTag(it.getString(R.string.dismisspopup_test_tag)), useUnmergedTree = true).assertIsDisplayed().performClick()
        }
    }

    @Test
    fun removeStudentButtonTestConfirm() {
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()
                viewModel.addStudent(Student("Edit Test", "test@test.com", "123-123-1233"))
                NavGraph(navController = navController, viewModel = viewModel)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed().performClick()}
        context?.let {
            composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNodeWithContentDescription(it.getString(R.string.remove_student))
                .assertIsDisplayed().performClick()
        }
        context?.let {
            composeTestRule.onNode(hasTestTag(it.getString(R.string.dialogtitle_test_tag))).assertIsDisplayed()
        }
        context?.let {
            composeTestRule.onNode(hasTestTag(it.getString(R.string.confirmpopup_test_tag)), useUnmergedTree = true).assertIsDisplayed().performClick()
        }
    }

    fun withRole(role: Role) = SemanticsMatcher("${SemanticsProperties.Role.name} contains '$role'") {
        val roleProperty = it.config.getOrNull(SemanticsProperties.Role) ?: false
        roleProperty == role
    }
}
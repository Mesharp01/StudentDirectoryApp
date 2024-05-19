package com.example.studentapp.screens

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.compose.StudentAppTheme
import com.example.studentapp.navigation.NavGraph
import com.example.studentapp.screens.MainScreen
import com.example.studentapp.viewModel.StudentViewModel
import org.junit.Rule
import org.junit.Test
import com.example.studentapp.R

class MainScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainScreenTest(){
        var context: Context? = null
        composeTestRule.setContent {
            context = LocalContext.current
            StudentAppTheme {
                val navController = rememberNavController()
                val viewModel = StudentViewModel()
                NavGraph(navController = navController, viewModel = viewModel)
            }
        }
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.main_screen_title)).assertIsDisplayed()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.add_student)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithContentDescription(it.getString(R.string.back_button_content_description)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.edit_student)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithContentDescription(it.getString(R.string.back_button_content_description)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.search_student)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithContentDescription(it.getString(R.string.back_button_content_description)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithText(it.getString(R.string.list_student)).assertIsDisplayed().performClick()}
        context?.let { composeTestRule.onNodeWithContentDescription(it.getString(R.string.back_button_content_description)).assertIsDisplayed().performClick()}
    }
}
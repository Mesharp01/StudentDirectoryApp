package com.example.studentapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.studentapp.data.ADD_SUCCESS_ARG
import com.example.studentapp.data.EMAIL_ARG
import com.example.studentapp.data.STUDENT_EMAIL_ARG
import com.example.studentapp.data.UPDATE_SUCCESS_ARG
import com.example.studentapp.viewModel.StudentViewModel
import com.example.studentapp.screens.EditAndSearch
import com.example.studentapp.screens.EditStudentPage
import com.example.studentapp.screens.MainScreen
import com.example.studentapp.screens.SearchBarAndList
import com.example.studentapp.screens.ViewAllStudents


@Composable
fun NavGraph(navController: NavHostController, viewModel: StudentViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavConstants.MAIN_SCREEN,
    ){
        composable(
            NavConstants.MAIN_SCREEN){entry ->
            val validStudent = entry.savedStateHandle.get<Boolean>(ADD_SUCCESS_ARG)
            MainScreen(navController, validStudent)
            entry.savedStateHandle.remove<Boolean>(ADD_SUCCESS_ARG)
        }
        composable(NavConstants.VIEW_ALL_STUDENT_PAGE){ entry ->
            val studentUpdate = entry.savedStateHandle.get<Boolean>(UPDATE_SUCCESS_ARG)
            ViewAllStudents(viewModel, navController, studentUpdate)
            entry.savedStateHandle.remove<Boolean>(UPDATE_SUCCESS_ARG)
        }
        composable("${NavConstants.EDIT_STUDENT_PAGE}${EMAIL_ARG}",
            arguments = listOf(
                navArgument(STUDENT_EMAIL_ARG){
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType }
            )
        ){
                backStackEntry ->
            val studentEmail = backStackEntry.arguments?.getString("studentEmail")
            EditStudentPage(studentEmail, viewModel, navController)
        }
        composable(NavConstants.SEARCH_STUDENT_PAGE){entry ->
            val studentUpdate = entry.savedStateHandle.get<Boolean>(UPDATE_SUCCESS_ARG)
            SearchBarAndList(viewModel, navController, studentUpdate)
            entry.savedStateHandle.remove<Boolean>(UPDATE_SUCCESS_ARG)
        }
        composable(NavConstants.REMOVE_STUDENT_FROM_VIEW){
            ViewAllStudents(viewModel, navController)
        }
        composable(NavConstants.EDIT_STUDENT_PAGE_WITH_SEARCH){entry ->
            val studentUpdate = entry.savedStateHandle.get<Boolean>(UPDATE_SUCCESS_ARG)
            EditAndSearch(viewModel, navController, studentUpdate)
            entry.savedStateHandle.remove<Boolean>(UPDATE_SUCCESS_ARG)
        }
        composable(NavConstants.REMOVE_STUDENT_FROM_SEARCH){
            SearchBarAndList(viewModel, navController)
        }
    }
}

object NavConstants{
    const val EDIT_STUDENT_PAGE = "editStudent?"
    const val SEARCH_STUDENT_PAGE = "searchStudent"
    const val VIEW_ALL_STUDENT_PAGE = "studentList"
    const val MAIN_SCREEN = "mainScreen"
    const val REMOVE_STUDENT_FROM_VIEW = "removeStudentFromList"
    const val REMOVE_STUDENT_FROM_SEARCH = "removeStudentFromSearch"
    const val EDIT_STUDENT_PAGE_WITH_SEARCH = "editAndSearch"
}
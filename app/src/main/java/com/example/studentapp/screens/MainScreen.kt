package com.example.studentapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studentapp.navigation.NavConstants
import com.example.studentapp.R
import com.example.studentapp.components.DefaultButton
import com.example.studentapp.components.StudentToolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, validStudent: Boolean? = false) {
    val context = LocalContext.current
    if(validStudent == true){
        Toast.makeText(context, stringResource(R.string.student_add_success_msg), Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        topBar = {
            StudentToolBar(
                onClick = { navController.popBackStack() },
                title = stringResource(id = R.string.main_screen_title)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MainPageButtons(navController)
            }
        })
}

@Composable
fun MainPageButtons(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .absolutePadding(50.dp, 200.dp, 50.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DefaultButton(buttonText = stringResource(id = R.string.add_student),
            onClick = { navController.navigate("${NavConstants.EDIT_STUDENT_PAGE}") },
            enabled = true)
        DefaultButton(
            buttonText = stringResource(id = R.string.edit_student),
            onClick = { navController.navigate(NavConstants.EDIT_STUDENT_PAGE_WITH_SEARCH) },
            enabled = true)
        DefaultButton(
            buttonText = stringResource(id = R.string.search_student),
            onClick = { navController.navigate(NavConstants.SEARCH_STUDENT_PAGE) },
            enabled = true)
        DefaultButton(
            buttonText = stringResource(id = R.string.list_student),
            onClick = { navController.navigate(NavConstants.VIEW_ALL_STUDENT_PAGE) },
            enabled = true)
    }
}

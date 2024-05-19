package com.example.studentapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.StudentAppTheme
import com.example.studentapp.navigation.NavGraph
import com.example.studentapp.viewModel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    ) {
                        val navController = rememberNavController()
                        val viewModel: StudentViewModel = viewModel()
                        NavGraph(navController = navController, viewModel = viewModel)
                    }
                }
        }
    }
}


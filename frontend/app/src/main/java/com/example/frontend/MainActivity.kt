package com.example.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend.domain.SearchViewModel
import com.example.frontend.ui.screens.ResultScreen
import com.example.frontend.ui.screens.SearchScreen
import com.example.frontend.ui.theme.FrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val searchViewModel: SearchViewModel = viewModel()
                    NavHost(navController = navController, startDestination = "search") {
                        composable("search") { SearchScreen(navController, searchViewModel) }
                        composable("result") { ResultScreen(navController, searchViewModel) }
                    }
                }
            }
        }
    }
}
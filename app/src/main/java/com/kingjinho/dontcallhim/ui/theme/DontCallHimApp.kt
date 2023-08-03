package com.kingjinho.dontcallhim.ui.theme

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kingjinho.dontcallhim.screen.add.AddNumberScreen
import com.kingjinho.dontcallhim.screen.main.MainScreen
import com.kingjinho.dontcallhim.viewmodels.OutgoingCallVM

@Composable
fun DontCallHimApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {

        composable("main") {
            MainScreen(onAddNumberClick = {
                navController.navigate("addNumber")
            })
        }

        composable("addNumber") {
            val viewModel = hiltViewModel<OutgoingCallVM>()
            AddNumberScreen(viewModel)
        }
    }
}
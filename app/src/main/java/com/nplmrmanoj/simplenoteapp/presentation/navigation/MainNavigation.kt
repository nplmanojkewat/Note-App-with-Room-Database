package com.nplmrmanoj.simplenoteapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nplmrmanoj.simplenoteapp.presentation.screens.InsertNoteScreen
import com.nplmrmanoj.simplenoteapp.presentation.screens.MainActivity
import com.nplmrmanoj.simplenoteapp.presentation.screens.MainScreen
import com.nplmrmanoj.simplenoteapp.presentation.viewmodel.NoteViewModel


@Composable
fun MainNavigation(viewModel: NoteViewModel, mainActivity: MainActivity) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainScreen) {

        composable<MainScreen>{
            MainScreen(viewModel, mainActivity, navController)
        }

        composable<InsertNote>{
            val id = it.toRoute<InsertNote>()
            InsertNoteScreen(viewModel, navController, id.id)
        }

    }

}
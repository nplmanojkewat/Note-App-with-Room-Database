package com.nplmrmanoj.simplenoteapp.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.room.Room
import com.nplmrmanoj.simplenoteapp.data.remote.NoteDatabase
import com.nplmrmanoj.simplenoteapp.data.repository.NoteRepository
import com.nplmrmanoj.simplenoteapp.presentation.navigation.MainNavigation
import com.nplmrmanoj.simplenoteapp.presentation.viewmodel.NoteViewModel
import com.nplmrmanoj.simplenoteapp.presentation.viewmodel.NoteViewModelFactory
import com.nplmrmanoj.simplenoteapp.ui.theme.SimpleNoteAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var database: NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "Note.DB"
        ).build()


        val viewModel by viewModels<NoteViewModel> {
            NoteViewModelFactory(NoteRepository(database))
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNoteAppTheme {
                MainNavigation(viewModel, this)
            }
        }
    }
}


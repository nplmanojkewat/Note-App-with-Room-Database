package com.nplmrmanoj.simplenoteapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nplmrmanoj.simplenoteapp.data.repository.NoteRepository

public class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
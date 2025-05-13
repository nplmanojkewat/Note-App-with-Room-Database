package com.nplmrmanoj.simplenoteapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nplmrmanoj.simplenoteapp.data.model.Note
import com.nplmrmanoj.simplenoteapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    fun getNotes() = noteRepository.getNote().asLiveData(viewModelScope.coroutineContext)

    private val _noteById = MutableStateFlow<Note?>(null)
    val noteById = _noteById

    fun upsertNote(note: Note){
        viewModelScope.launch {
            noteRepository.upsertNote(note)
        }
    }


    fun getNoteByID(id: Int) {
        viewModelScope.launch {
            val note = noteRepository.getNoteByID(id).first()
            _noteById.value = note
        }
    }


    fun updateNote(note: Note){
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }


    fun deleteNote(id: Int){
        viewModelScope.launch {
            noteRepository.deleteNote(id)
        }
    }


}
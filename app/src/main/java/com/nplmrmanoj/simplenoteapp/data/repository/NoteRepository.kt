package com.nplmrmanoj.simplenoteapp.data.repository

import androidx.lifecycle.LiveData
import com.nplmrmanoj.simplenoteapp.data.model.Note
import com.nplmrmanoj.simplenoteapp.data.model.NoteDao
import com.nplmrmanoj.simplenoteapp.data.remote.NoteDatabase
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val db: NoteDatabase) {

    fun getNote() = db.noteDao.getNote()

    suspend fun upsertNote(note: Note){
        db.noteDao.upsertNote(note)
    }

    suspend fun updateNote(note: Note){
        db.noteDao.updateNote(note)
    }

    suspend fun deleteNote(id: Int){
        db.noteDao.deleteNote(id)
    }

    fun getNoteByID(id: Int): Flow<Note?> {
        return db.noteDao.getNoteByID(id)
    }

}
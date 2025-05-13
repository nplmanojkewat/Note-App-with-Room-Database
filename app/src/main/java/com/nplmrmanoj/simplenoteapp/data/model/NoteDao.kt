package com.nplmrmanoj.simplenoteapp.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM 'NoteData'")
    fun getNote() : Flow<List<Note>>

    @Query("SELECT * FROM NoteData WHERE id = :id")
    fun getNoteByID(id: Int): Flow<Note?>

    @Upsert
    suspend fun upsertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM NoteData WHERE id = :id")
    suspend fun deleteNote(id: Int)



}
package com.nplmrmanoj.simplenoteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("NoteData")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val priority: String = "LOW",
    val createdTime: Long = System.currentTimeMillis()
)

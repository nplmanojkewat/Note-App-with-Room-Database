package com.nplmrmanoj.simplenoteapp.data.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nplmrmanoj.simplenoteapp.data.model.Note
import com.nplmrmanoj.simplenoteapp.data.model.NoteDao


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

//    companion object {
//        const val DATABASE_NAME = "Simple Note App"
//    }
//
//    @Volatile
//    var INSTANCE : NoteDatabase? = null
//
//
//    fun getDatabase(context: Context) : NoteDatabase {
//        return INSTANCE ?: synchronized(this){
//
//            val instance = Room.databaseBuilder(
//                context,
//                NoteDatabase::class.java,
//                DATABASE_NAME
//            ).build()
//
//            INSTANCE = instance
//            instance
//
//        }
//    }

}
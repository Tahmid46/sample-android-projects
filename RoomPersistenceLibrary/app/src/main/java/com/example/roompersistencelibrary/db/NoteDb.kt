package com.example.roompersistencelibrary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDb : RoomDatabase() {
    abstract fun getDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "note-db"
        private var instance: NoteDb? = null
        private val lock = Any()

        /**
         * This method is called at the time of instantiation.
         * Example: When we write , val db = NoteDb(context), that time this function is called. So no need
         * to call it separately.
         */
        operator fun invoke(context: Context): NoteDb = instance ?: synchronized(lock) {
            instance ?: buildRoomDb(context).also {
                instance = it
            }
        }

        private fun buildRoomDb(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                NoteDb::class.java,
                DATABASE_NAME
        ).build()
    }
}
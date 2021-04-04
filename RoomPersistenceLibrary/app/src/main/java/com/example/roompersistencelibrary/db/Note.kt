package com.example.roompersistencelibrary.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Entity is the table.
 * Each property of entity class is a column and property name would be the column name by default.
 * If we want to specify another name for the column then we need to annotate that with the property with @ColumnInfo(name = "Column name")
 * Each instance of the class is  added as a new row to the table.
 */
@Entity
data class Note(
        @ColumnInfo(name = "Title") val title: String,
        @ColumnInfo(name = "Note") val note: String,
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
) : Serializable

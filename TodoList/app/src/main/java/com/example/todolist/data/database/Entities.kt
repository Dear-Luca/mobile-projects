package com.example.todolist.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // add = 0 to not specify id in the constructor
    @ColumnInfo val content : String,
    @ColumnInfo val isComplete: Boolean = false
)
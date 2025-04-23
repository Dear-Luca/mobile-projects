package com.example.traveldiary.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Create database table (entity)
 */
@Entity
data class Trip(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val date: String,

    @ColumnInfo
    val description: String

)
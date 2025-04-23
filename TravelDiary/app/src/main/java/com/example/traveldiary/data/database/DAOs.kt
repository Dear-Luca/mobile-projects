package com.example.traveldiary.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/*
Data Access Object for Trip Entities
 */
@Dao
interface TripsDAO{
    @Query("SELECT * FROM trip")
    fun getAll()  : Flow<List<Trip>>

    @Upsert
    fun upsert(trip: Trip)

    @Delete
    fun delete(trip: Trip)
}
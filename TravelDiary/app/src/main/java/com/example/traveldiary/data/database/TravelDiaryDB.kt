package com.example.traveldiary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Trip::class], version = 1)
abstract class TravelDiaryDB : RoomDatabase() {
    abstract fun tripsDao() : TripsDAO
}
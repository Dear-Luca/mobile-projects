package com.example.traveldiary.data.repositories

import com.example.traveldiary.data.database.Trip
import com.example.traveldiary.data.database.TripsDAO
import kotlinx.coroutines.flow.Flow

/*
Link between db and the app (take DAO as input)
 */
class TripsRepository(private val tripsDAO: TripsDAO) {
    val trips: Flow<List<Trip>> = tripsDAO.getAll()

    suspend fun upsert(trip: Trip) = tripsDAO.upsert(trip)

    suspend fun delete(trip: Trip) = tripsDAO.delete(trip)
}
package com.example.traveldiary.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

/*
class that takes DataStore as parameter to save the username in memory (as key-value pair)
 */
class SettingsRepository(private val dataStore: DataStore<Preferences>) {
    /*
    static USERNAME_KEY
     */
    companion object{
        private val USERNAME_KEY = stringPreferencesKey("username")
    }

    /*
    read the new value of username
     */
    val username = dataStore.data.map { preferences -> preferences[USERNAME_KEY] ?: ""}

    /*
    write and update datastore to save the new value of username
     */
    suspend fun setUsername(username: String) = dataStore.edit { preferences -> preferences[USERNAME_KEY] = username}
}
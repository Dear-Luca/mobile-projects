package com.example.traveldiary

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.traveldiary.data.repositories.SettingsRepository
import com.example.traveldiary.ui.screens.addtravel.AddTravelViewModel
import com.example.traveldiary.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/*
I can access to dataStore from every Context
 */
val Context.dataStore by preferencesDataStore("settings")

/*
Koin module, where dependency injections are defined
 */

val koinModule = module {
    /*
    Get a koin instance (Singleton) of Context and access to its dataStore.
     */
    single { get<Context>().dataStore }
    /*
    Provide to SettingsRepository the DataStore created the prev line.
    It's like SettingsRepository(dataStore)
     */
    single { SettingsRepository(get()) }
    viewModel { AddTravelViewModel() }
    /*
    It's like SettingsViewModel(repository = SettingsRepository(dataStore))
     */
    viewModel { SettingsViewModel(get()) }
}
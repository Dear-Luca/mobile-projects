package com.example.traveldiary.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveldiary.data.repositories.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class SettingsState(
    val username: String = ""
)

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    var state by mutableStateOf(SettingsState(""))
        private set

    fun setUsername(username: String) {
        /*
        change the state for the viewModel
         */
        state = SettingsState(username)
        /*
        update username in the datastore
         */
        viewModelScope.launch {
            repository.setUsername(username)
        }
    }

    /*
    initialize the username with the value in the datastore
     */
    init {
        viewModelScope.launch {
            state = SettingsState(repository.username.first())
        }
    }
}
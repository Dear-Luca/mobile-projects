package com.example.traveldiary.ui.screens.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsState(
    val username: String = ""
)

class SettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    fun setUsername(newUsername: String){
        _state.update { currentState ->
            currentState.copy(
                username = newUsername
            )
        }
    }
}
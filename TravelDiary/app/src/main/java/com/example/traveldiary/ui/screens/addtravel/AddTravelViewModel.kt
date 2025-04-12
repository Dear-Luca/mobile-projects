package com.example.traveldiary.ui.screens.addtravel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
Convention: Grouping state of the ViewModel into a state class (data class)
 */
data class AddTravelState(
    val destination: String = "",
    val date: String = "",
    val description: String = "",
)

interface AddTravelActions{
    fun setDestination(destination: String)
    fun setDate(date: String)
    fun setDescription(description: String)
}

class AddTravelViewModel : ViewModel() {
    /*
    MutableStateFlow as backing property, so I can change it internally and expose a StateFlow (read-only)
     */
    private val _state = MutableStateFlow(AddTravelState())
    /*
    public read-only StateFlow
     */
    val state = _state.asStateFlow()

    val actions = object : AddTravelActions {
        override fun setDestination(destination: String) {
            _state.update { currentState ->
                currentState.copy(
                    destination = destination
                )
            }
        }

        override fun setDate(date: String) {
            _state.update { currentState ->
                currentState.copy(
                    date = date
                )
            }
        }

        override fun setDescription(description: String) {
            _state.update { currentState ->
                currentState.copy(
                    description = description
                )
            }
        }
    }
}
package com.example.traveldiary.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.traveldiary.ui.screens.addtravel.AddTravelScreen
import com.example.traveldiary.ui.screens.addtravel.AddTravelViewModel
import com.example.traveldiary.ui.screens.home.HomeScreen
import com.example.traveldiary.ui.screens.settings.SettingsScreen
import com.example.traveldiary.ui.screens.settings.SettingsViewModel
import com.example.traveldiary.ui.screens.traveldetails.TravelDetailsScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

sealed interface TravelDiaryRoute{
    @Serializable
    data class TravelDetails(val travelId: String) : TravelDiaryRoute
    @Serializable
    data object AddTravel : TravelDiaryRoute
    @Serializable
    data object Settings : TravelDiaryRoute
    @Serializable
    data object TravelDiary : TravelDiaryRoute
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = TravelDiaryRoute.TravelDiary
    ){
        /*
        Routes Definition
         */
        composable<TravelDiaryRoute.TravelDiary> { HomeScreen(navController) }
        composable<TravelDiaryRoute.AddTravel> {
            val addTravelViewModel = koinViewModel<AddTravelViewModel>()
            val addTravelState by addTravelViewModel.state.collectAsStateWithLifecycle()
            AddTravelScreen(navController, addTravelState, addTravelViewModel.actions)

        }
        composable<TravelDiaryRoute.Settings> {
            val settingsViewModel = koinViewModel<SettingsViewModel>()
            SettingsScreen(navController, settingsViewModel::setUsername, settingsViewModel.state)
        }
        composable<TravelDiaryRoute.TravelDetails> {
                backStackEntry -> val travelDetails : TravelDiaryRoute.TravelDetails = backStackEntry.toRoute()
            TravelDetailsScreen(navController, travelDetails.travelId)
        }
    }
}
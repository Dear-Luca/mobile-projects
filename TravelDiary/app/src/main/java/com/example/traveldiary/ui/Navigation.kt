package com.example.traveldiary.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    data class TravelDetails(val travelId: Int) : TravelDiaryRoute
    @Serializable
    data object AddTravel : TravelDiaryRoute
    @Serializable
    data object Settings : TravelDiaryRoute
    @Serializable
    data object TravelDiary : TravelDiaryRoute
}

@Composable
fun NavGraph(navController: NavHostController){
    val tripsViewModel = koinViewModel<TripsViewModel>()
    val tripState by tripsViewModel.state.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = TravelDiaryRoute.TravelDiary
    ){
        /*
        Routes Definition
         */
        composable<TravelDiaryRoute.TravelDiary> { HomeScreen(tripState ,navController) }
        composable<TravelDiaryRoute.AddTravel> {
            val addTravelViewModel = koinViewModel<AddTravelViewModel>()
            val addTravelState by addTravelViewModel.state.collectAsStateWithLifecycle()
            AddTravelScreen(navController, addTravelState, addTravelViewModel.actions)

        }
        composable<TravelDiaryRoute.Settings> {
            val settingsViewModel = koinViewModel<SettingsViewModel>()
            SettingsScreen(navController, settingsViewModel::setUsername, settingsViewModel.state)
        }
        composable<TravelDiaryRoute.TravelDetails> { backStackEntry ->
            val route : TravelDiaryRoute.TravelDetails = backStackEntry.toRoute()
            val trip = requireNotNull( tripState.trips.find { it.id ==  route.travelId})
            TravelDetailsScreen(navController, trip)
        }
    }
}
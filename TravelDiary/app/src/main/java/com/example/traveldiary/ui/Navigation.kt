package com.example.traveldiary.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.traveldiary.ui.screens.AddTravelScreen
import com.example.traveldiary.ui.screens.HomeScreen
import com.example.traveldiary.ui.screens.SettingsScreen
import com.example.traveldiary.ui.screens.TravelDetailsScreen
import kotlinx.serialization.Serializable

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
        composable<TravelDiaryRoute.AddTravel> { AddTravelScreen(navController) }
        composable<TravelDiaryRoute.Settings> { SettingsScreen(navController) }
        composable<TravelDiaryRoute.TravelDetails> {
                backStackEntry -> val travelDetails : TravelDiaryRoute.TravelDetails = backStackEntry.toRoute()
            TravelDetailsScreen(navController, travelDetails.travelId)
        }
    }
}
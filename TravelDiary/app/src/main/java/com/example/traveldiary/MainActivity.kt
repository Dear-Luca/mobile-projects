package com.example.traveldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.traveldiary.ui.composables.AppBar
import com.example.traveldiary.ui.screens.AddTravelScreen
import com.example.traveldiary.ui.screens.HomeScreen
import com.example.traveldiary.ui.screens.SettingsScreen
import com.example.traveldiary.ui.screens.TravelDetailsScreen
import com.example.traveldiary.ui.theme.TravelDiaryTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelDiaryTheme {
                val navController = rememberNavController()
                NavGraph(navController)

            }
        }
    }
}

sealed interface TravelDiaryRoute{
    @Serializable
    data class TravelDetails(val travelId: String) : TravelDiaryRoute
    @Serializable
    data object AddTravel : TravelDiaryRoute
    @Serializable
    data object Settings : TravelDiaryRoute
    @Serializable
    data object TravelDiary : TravelDiaryRoute
    /*
    SHARE BUTTON : TODO
     */
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




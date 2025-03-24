package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {AppBar(navController)},
                    modifier = Modifier.fillMaxSize()

                ) { innerPadding ->
                    NavGraph(navController, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

sealed interface NavigationRoute{
    @Serializable
    data object  Screen1
    @Serializable
    data object  Screen2
    @Serializable
    data object  Screen3
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = NavigationRoute.Screen1,
    ){
        this.composable<NavigationRoute.Screen1> { Screen1(navController) }
        this.composable<NavigationRoute.Screen2> { Screen2(navController) }
        this.composable<NavigationRoute.Screen3> { Screen3(navController) }

    }
}


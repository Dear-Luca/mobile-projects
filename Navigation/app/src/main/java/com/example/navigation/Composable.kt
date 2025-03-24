package com.example.navigation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun Screen1(navController: NavHostController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ){
        Button(onClick = {navController.navigate(NavigationRoute.Screen2)}, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor =  MaterialTheme.colorScheme.onPrimary
        ),
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2)
        ) {
            Text("Screen 2")
        }
    }
}

@Composable
fun Screen2(navController: NavHostController){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Button(onClick = {navController.navigateUp()}, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor =  MaterialTheme.colorScheme.onSecondary
        ),
            modifier = Modifier.weight(1F)
        ) {
            Text("Screen 1")
        }

        Button(onClick = {navController.navigate(NavigationRoute.Screen3)}, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor =  MaterialTheme.colorScheme.onSecondary
        ),
            modifier = Modifier.weight(1F)
        ) {
            Text("Screen 3")
        }

    }

}

@Composable
fun Screen3(navController: NavHostController){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ){
        Button(onClick = {navController.navigateUp()}, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor =  MaterialTheme.colorScheme.onTertiary
        ),
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2)
        ) {
            Text("Screen 2")
        }
    }
}
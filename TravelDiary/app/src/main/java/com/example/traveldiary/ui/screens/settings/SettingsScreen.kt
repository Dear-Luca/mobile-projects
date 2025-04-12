package com.example.traveldiary.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.ui.composables.AppBar

@Composable
fun SettingsScreen(navController: NavHostController, changeUsername: (String) -> Unit, state: SettingsState){
    Scaffold(
        topBar = { AppBar(navController, title = "Settings") }
    ){contentPadding -> Column (
        Modifier.padding(contentPadding).padding(10.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
            Settings(state, changeUsername)
    }  }
}

@Composable
fun Settings(state: SettingsState, changeUsername: (String) -> Unit){
    OutlinedTextField(
        value = state.username,
        onValueChange = { changeUsername(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Username")}
    )
    Spacer(Modifier.size(36.dp))
    Text(
        text = state.username,
        style = MaterialTheme.typography.bodyLarge
    )
}



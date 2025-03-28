package com.example.traveldiary.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(){
    Scaffold(
        topBar = {SettingsAppBar()}
    ) {contentPadding -> Column (
        Modifier.padding(contentPadding).padding(10.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
            Settings()
    }  }
}

@Composable
fun Settings(){
    OutlinedTextField(
        value = "Username",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Username")}
    )
    Spacer(Modifier.size(36.dp))
    Text(
        text = "Username",
        style = MaterialTheme.typography.bodyLarge
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(){
    CenterAlignedTopAppBar(
        title = { Text("Settings")},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant)
    )
}
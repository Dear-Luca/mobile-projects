package com.example.traveldiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Date

@Composable
fun TravelDetailsScreen(){
    Scaffold(
        topBar = {
            TravelDetailsBar()
        },
    ){
        paddingValues -> Box(
            modifier = Modifier.padding(paddingValues),
        ){
            TravelDetails()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelDetailsBar(){
    CenterAlignedTopAppBar(
        title = { Text("Travel Details")},
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Settings, "Settings")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Composable
fun TravelDetails(){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(PaddingValues(top = 30.dp)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            Icons.Outlined.Image,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(30.dp),
            contentDescription = "Travel Image"
        )
        Spacer(Modifier.size(20.dp))
        Text("Destination", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Text("01/01/2025")
        Spacer(Modifier.size(20.dp))
        Text("Description")
    }
}
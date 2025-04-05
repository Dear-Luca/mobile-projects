package com.example.traveldiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.TravelDiaryRoute
import com.example.traveldiary.ui.composables.AppBar

@Composable
fun AddTravelScreen(navController: NavHostController){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigateUp()}, contentColor = MaterialTheme.colorScheme.tertiary) {
                Icon(Icons.Filled.Check, "share button")
            }
        },
        topBar = { AppBar(navController, title = "Add Travel") },

    ) {paddingValues -> AddTravelDetails(navController, paddingValues)
    }
}


@Composable
fun AddTravelDetails(navController: NavHostController, paddingValues: PaddingValues) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .padding(12.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { Text("Destination") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Outlined.MyLocation, "Current location")
                }
            }
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /*TODO*/ },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(24.dp))
        Button(
            onClick = { /*TODO*/ },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        ) {
            Icon(
                Icons.Outlined.PhotoCamera,
                contentDescription = "Camera icon",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Take a picture")
        }
        Spacer(Modifier.size(8.dp))
        Image(
            Icons.Outlined.Image,
            "Travel picture",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(36.dp)
        )
    }
}
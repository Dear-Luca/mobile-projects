package com.example.gps

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gps.ui.theme.GPSTheme
import com.example.gps.utils.LocationService
import com.example.gps.utils.PermissionStatus
import com.example.gps.utils.rememberMultiplePermissions
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GPSTheme {
                GPSScreen()
            }
        }
    }
}

@Composable
fun GPSScreen(){
    val context = LocalContext.current
    var showLocationDisabledWarning by remember { mutableStateOf(false) }
    var showLocationDeniedWarning by remember { mutableStateOf(false) }
    var showLocationPermanentlyDeniedWarning by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    val locationService = remember { LocationService(context) }
    val coordinates by locationService.coordinates.collectAsStateWithLifecycle()
    val isLoading by locationService.isLoadingLocation.collectAsStateWithLifecycle()

    // Function to get location
    val scope = rememberCoroutineScope()
    fun getCurrentLocation() = scope.launch {
        try {
            locationService.getCurrentLocation()
        } catch (_ : IllegalStateException){
            showLocationDisabledWarning = true
        }
    }

    // Permissions handling
    val locationPermission = rememberMultiplePermissions(
        listOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    ) { statuses ->
        when {
            statuses.any {it.value.isGranted} ->
                getCurrentLocation()
            statuses.all { it.value == PermissionStatus.PemanentlyDenied } ->
                showLocationPermanentlyDeniedWarning = true
            else -> showLocationDeniedWarning = true
        }
    }

    fun getLocationOrRequestPermission(){
        if (locationPermission.statuses.any { it.value.isGranted }) {
            getCurrentLocation()
        } else {
            locationPermission.launchPermissionRequest()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)},
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if(isLoading){
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Button(onClick = ::getLocationOrRequestPermission) {
                Text("Get current location")
            }
            Spacer(Modifier.height(16.dp))
            Text("Latitude:  ${coordinates?.latitude ?: "_"}")
            Text("Longitude:  ${coordinates?.longitude ?: "_"}")
        }
    }
}
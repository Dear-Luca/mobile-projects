package com.example.gps.utils

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Intent
import android.provider.Settings
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

data class Coordinates(val latitude: Double, val longitude: Double)

class LocationService(private val context: Context){
    // client to access location (fused -> gps and cellular, both)
    private val fusedLocationClient = getFusedLocationProviderClient(context)
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    // properties for coordinates and loading
    private val _coordinates = MutableStateFlow<Coordinates?>(null)
    val coordinates = _coordinates.asStateFlow()

    private val _isLoadingLocation = MutableStateFlow(false)
    val isLoadingLocation = _isLoadingLocation.asStateFlow()

    // function to get the location
    suspend fun getCurrentLocation(): Coordinates? {
        // check if location is disabled
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            throw  IllegalStateException("Location is disabled")
        }

        // check if permissions are missing
        if (
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            throw SecurityException("Location permission not granted")
        }

        // get location
        _isLoadingLocation.value = true
        val location = withContext(Dispatchers.IO){
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).await()
        }
        _isLoadingLocation.value = false

        _coordinates.value =
            if (location != null) Coordinates(location.latitude, location.longitude)
            else null
        return coordinates.value
    }

    // function to open location settings
    fun openLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        if(intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}
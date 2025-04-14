package com.example.gps.utils

import android.content.pm.PackageManager
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat

enum class PermissionStatus{
    Unkown,
    Granted,
    Denied,
    PemanentlyDenied;

    val isGranted get() = this == Granted
    val isDenied get() = this == Denied || this == PemanentlyDenied
}

interface MultiplePermissionHandler{
    val statuses: Map<String, PermissionStatus>
    fun launchPermissionRequest()
}

@Composable
fun rememberMultiplePermissions(
    permissions: List<String>,
    onResult : (status: Map<String, PermissionStatus>) -> Unit
): MultiplePermissionHandler {
    val activity = LocalActivity.current!!

    // MutableState for permission status
    var statuses by remember {
        mutableStateOf(
            permissions.associateWith { permissions ->
                if (
                    ContextCompat.checkSelfPermission(activity, permissions) == PackageManager.PERMISSION_GRANTED
                ) {
                    PermissionStatus.Granted
                } else {
                    PermissionStatus.Unkown
                }
            }
        )
    }

    // Launcher to request permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { newPermissions ->
        statuses = newPermissions.mapValues { (permission, isGranted) ->
            when {
                isGranted -> PermissionStatus.Granted
                activity.shouldShowRequestPermissionRationale(permission) -> PermissionStatus.Denied
                else -> PermissionStatus.PemanentlyDenied
            }
        }
        onResult(statuses)
    }

    // MultiplePermissionHandler creation
    val permissionHandler = remember(permissionLauncher) {
        object : MultiplePermissionHandler {
            override val statuses: Map<String, PermissionStatus>
                get() = statuses

            override fun launchPermissionRequest() {
                permissionLauncher.launch(permissions.toTypedArray())
            }
        }
    }
    return permissionHandler
}


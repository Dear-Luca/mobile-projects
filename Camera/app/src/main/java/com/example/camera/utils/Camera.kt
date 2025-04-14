package com.example.camera.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File

interface CameraLauncher{
    val captureImageUri : Uri
    fun captureImage()

}

@Composable
fun rememberCameraLauncher(
    onPictureTaken : (Uri) -> Unit = {}
) : CameraLauncher{
    val context = LocalContext.current
    /*
    Create a jpg image in the cache of the app and get the URI of if
     */
    val imageUri = remember {
        val imageFile = File.createTempFile("tmp_image", "jpg", context.externalCacheDir)
        FileProvider.getUriForFile(context, context.packageName + ".provider", imageFile)
    }

    var capturedImageUri by remember { mutableStateOf(Uri.EMPTY) }
    /*
    launch camera activity with a callback
     */
    val cameraActivityLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { pictureTaken ->
        if (pictureTaken){
            capturedImageUri = imageUri
            onPictureTaken(capturedImageUri)
        }
    }

    /*
    if cameraActivityLauncher changes, recalculate cameraLauncher
     */
    val cameraLauncher = remember(cameraActivityLauncher) {
        object : CameraLauncher{
            override val captureImageUri: Uri
                get() = capturedImageUri

            override fun captureImage() {
                cameraActivityLauncher.launch(imageUri)
            }
        }
    }
    return cameraLauncher
}
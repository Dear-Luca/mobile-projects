package com.example.traveldiary.ui.screens

import android.content.Intent
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.traveldiary.ui.composables.AppBar

@Composable
fun TravelDetailsScreen(navController: NavHostController, travelId : String) {
    val context = LocalContext.current
    fun shareContent(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, travelId)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Travel")
        context.startActivity(shareIntent)

    }

    Scaffold(
        topBar = { AppBar(navController, title = "Travel Details") },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.tertiary,
                onClick = { shareContent() }
            ) {
                Icon(Icons.Outlined.Share, "Share Travel")
            }
        },
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(contentPadding).padding(12.dp).fillMaxSize()
        ) {
            Image(
                Icons.Outlined.Image,
                "Travel picture",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(36.dp)
            )
            Text(
                travelId,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "01/01/2024",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.size(8.dp))
            Text(
                "Description",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

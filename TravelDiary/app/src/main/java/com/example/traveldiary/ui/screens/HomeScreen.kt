package com.example.traveldiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            TravelTobBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, "add item")
            }
        }
    ) {paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)){
            TravelList()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelTobBar(){
    CenterAlignedTopAppBar(
        title = { Text("Travel Diary")},
        modifier = Modifier.padding(vertical = 4.dp),
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Search, "Search item")
            }
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
fun TravelList() {
    val elements = mutableListOf<String>()
    for (i in 1..20) {
        elements.add("Item $i")
    }

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(elements){
            item -> TravelListItem(item)
        }
    }
}

@Composable
fun TravelListItem(item: String){
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ){
        Column {
            Icon(
                Icons.Filled.Image,
                "element image",
                modifier = Modifier.size(60.dp)
            )
            Text(item)

        }
    }
}
package com.example.traveldiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){
    val elems = (0..100).map { "Item n $it" }
    LazyColumn(
    ) {
        items(elems){
            item -> HomeScreenItem(item)
        }
    }
}

@Composable
fun HomeScreenItem(item : String){
    Card() {
        Text(text = item)

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    HomeScreen()
}
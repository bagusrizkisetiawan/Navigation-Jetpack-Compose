package com.bagusrizki.navigation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen(username:String?){
    Column(
        modifier = Modifier.fillMaxSize(), // Mengisi ukuran penuh layar
        verticalArrangement = Arrangement.Center, // Pusatkan secara vertikal
        horizontalAlignment = Alignment.CenterHorizontally // Pusatkan secara horizontal
    ) {
        Text(text = "Profile $username")
    }
}
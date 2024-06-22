package com.bagusrizki.navigation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val inputText = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp), // Mengisi ukuran penuh layar dan memberi padding
                verticalArrangement = Arrangement.Center, // Pusatkan secara vertikal
                horizontalAlignment = Alignment.CenterHorizontally // Pusatkan secara horizontal
            ) {
                Text(text = "Home Screen")
                // Input field untuk teks
                OutlinedTextField(
                    value = inputText.value,
                    onValueChange = { inputText.value = it },
                    label = { Text("Enter your name") },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                )

                // Tombol untuk navigasi ke ProfileScreen dengan membawa input text sebagai argumen
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small, // Mengatur bentuk tombol menjadi bulat dengan radius small
                    onClick = {
                        val username = inputText.value.text.trim()
                        if (username.isNotEmpty()) {
                            // Navigasi ke ProfileScreen dengan membawa username sebagai argumen
                            navController.navigate("profile/$username")
                        } else {
                            // Tampilkan pesan kesalahan jika input kosong
                            scope.launch {
                                snackbarHostState.showSnackbar("Please enter your name")
                            }
                        }
                    }
                ) {
                    Text(text = "Go to Profile", modifier = Modifier.padding(8.dp))
                }
            }
        }
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_3_XL)
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController())
}

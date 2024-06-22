package com.bagusrizki.navigation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.bagusrizki.navigation.R

// Fungsi komposabel untuk membuat TopNavigationBar dengan navigasi
@Composable
fun TopNavigationBar(navController: NavController) {
    // TopAppBar adalah komponen untuk membuat AppBar di bagian atas layar
    TopAppBar(
        title = {
            // Teks judul untuk TopAppBar
            Text(
                text = "Example Navigation", // Judul yang ditampilkan di TopAppBar
                color = MaterialTheme.colorScheme.primaryContainer // Warna teks judul
            )
        },
        backgroundColor = MaterialTheme.colorScheme.primary, // Warna latar belakang TopAppBar
        contentColor = MaterialTheme.colorScheme.primaryContainer, // Warna konten di dalam TopAppBar
        actions = {
            // IconButton untuk menambahkan tombol ikon di sebelah kanan TopAppBar
            IconButton(onClick = { navController.navigate("notification") }) { // Aksi saat tombol ikon diklik
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_notification), // Ikon untuk tombol
                    contentDescription = "Notification" // Deskripsi konten untuk aksesibilitas
                )
            }
        }
    )
}

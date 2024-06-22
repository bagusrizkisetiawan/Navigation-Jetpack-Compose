package com.bagusrizki.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bagusrizki.navigation.ui.components.BottomBar
import com.bagusrizki.navigation.ui.components.TopNavigationBar
import com.bagusrizki.navigation.ui.screens.HomeScreen
import com.bagusrizki.navigation.ui.screens.NotificationScreen
import com.bagusrizki.navigation.ui.screens.ProfileScreen
import com.bagusrizki.navigation.ui.screens.SettingScreen
import com.bagusrizki.navigation.ui.theme.NavigationTheme

// MainActivity adalah aktivitas utama dari aplikasi ini
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // Surface container menggunakan warna latar belakang dari tema
                Surface(
                    modifier = Modifier.fillMaxSize(), // Mengisi seluruh ukuran layar
                    color = MaterialTheme.colorScheme.background // Mengatur warna latar belakang
                ) {
                    MyApp() // Memanggil fungsi MyApp untuk menampilkan konten utama
                }
            }
        }
    }
}

// Fungsi komposabel utama untuk aplikasi ini
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController() // Membuat controller untuk navigasi
    Scaffold(
        topBar = { TopNavigationBar(navController = navController) }, // Menambahkan TopNavigationBar di bagian atas
        bottomBar = { BottomBar(navController = navController) } // Menambahkan BottomBar di bagian bawah
    ) {
        // NavHost untuk mengatur navigasi antar halaman
        NavHost(
            navController = navController, // Controller untuk navigasi
            startDestination = "home", // Halaman awal adalah "home"
            modifier = Modifier.padding(it) // Menambahkan padding untuk konten agar tidak tertutup oleh topBar dan bottomBar
        ) {


            composable("home") { HomeScreen(navController) } // Halaman "home"

            composable(
                "profile/{username}",
                arguments = listOf(navArgument("username") { type = NavType.StringType })
            ) { backStackEntry ->
                ProfileScreen(username = backStackEntry.arguments?.getString("username"))
            } // Halaman "profile"

            composable("settings") { SettingScreen() } // Halaman "settings"

            composable("notification") { NotificationScreen() } // Halaman "notification"
        }
    }
}

// Fungsi komposabel untuk menampilkan preview dari aplikasi ini
@Preview(showBackground = true, device = Devices.PIXEL_3_XL)
@Composable
fun GreetingPreview() {
    NavigationTheme {
        MyApp() // Menampilkan MyApp dalam mode preview
    }
}

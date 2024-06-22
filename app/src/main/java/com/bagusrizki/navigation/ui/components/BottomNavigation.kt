package com.bagusrizki.navigation.ui.components

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bagusrizki.navigation.R
import com.bagusrizki.navigation.SecondActivity

@Composable
fun BottomBar(navController: NavController) {
    // Mendapatkan context saat ini
    val context = LocalContext.current

    // Mendefinisikan item navigasi bawah
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, "Home"),
        BottomNavItem("intent", R.drawable.ic_replay, "Intent"),
        BottomNavItem("profile", R.drawable.ic_profile, "Profile"),
        BottomNavItem("settings", R.drawable.ic_setting, "Settings")
    )

    // Mengamati entry back stack saat ini
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Mendapatkan rute saat ini
    val currentRoute = navBackStackEntry?.destination?.route

    // Membuat komponen BottomNavigation
    BottomNavigation(
        elevation = 5.dp, // Elevasi dari BottomNavigation
        backgroundColor = MaterialTheme.colorScheme.primary, // Warna latar belakang BottomNavigation
        contentColor = MaterialTheme.colorScheme.onPrimary // Warna konten BottomNavigation
    ) {
        // Mengiterasi setiap item dalam daftar items
        items.forEach { item ->
            // Menentukan apakah item saat ini terpilih
            val isSelected = when {
                item.route == "intent" -> currentRoute == null // Kasus khusus untuk intent
                item.route == "profile" -> currentRoute?.startsWith("profile") == true // Memeriksa apakah rute dimulai dengan "profile"
                else -> currentRoute == item.route // Membandingkan rute saat ini dengan rute item
            }

            // Membuat komponen BottomNavigationItem
            BottomNavigationItem(
                icon = {
                    // Membuat ikon untuk item
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon), // Mengambil gambar vektor untuk ikon
                        contentDescription = null, // Deskripsi konten ikon
                        modifier = Modifier.size(32.dp) // Ukuran ikon
                    )
                },

                label = {
                    // Membuat label untuk item
                    if (isSelected){
                        Text(
                            text = item.label, // Teks label
                            color =  MaterialTheme.colorScheme.primaryContainer, // Warna teks
                            fontSize = 12.sp // Ukuran font teks
                        )
                    }else{
                        Text(text = "", fontSize = 0.sp)
                    }

                },
                selected = isSelected, // Menentukan apakah item terpilih
                onClick = {
                    // Menangani klik pada item
                    when (item.route) {
                        "intent" -> {
                            // Jika item adalah "intent", memulai activity baru
                            val intent = Intent(context, SecondActivity::class.java).apply {
                                putExtra("username", "Bagus Rizki") // Menambahkan ekstra ke intent
                            }
                            context.startActivity(intent) // Memulai activity
                        }
                        "profile" -> {
                            // Jika item adalah "profile", menavigasi ke rute dengan parameter
                            navController.navigate("${item.route}/Bagus Rizki") {
                                popUpTo(navController.graph.startDestinationId) // Mengatur popUpTo untuk mencegah tumpukan back stack bertambah
                                launchSingleTop = true // Meluncurkan hanya satu instance dari destinasi
                            }
                        }
                        else -> {
                            // Menavigasi ke rute lain
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) // Mengatur popUpTo untuk mencegah tumpukan back stack bertambah
                                launchSingleTop = true // Meluncurkan hanya satu instance dari destinasi
                            }
                        }
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primaryContainer, // Warna konten yang dipilih
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary, // Warna konten yang tidak dipilih
                modifier = if(isSelected)Modifier.padding(vertical = 8.dp) else Modifier.padding(top = 14.dp) // Padding vertikal untuk item
            )
        }
    }
}

// Data class untuk item navigasi bawah
data class BottomNavItem(
    val route: String, // Rute item
    val icon: Int, // ID ikon item
    val label: String // Label item
)

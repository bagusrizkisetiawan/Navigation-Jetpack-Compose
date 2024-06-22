# Navigation Jetpack Compose

Proyek ini adalah contoh implementasi Jetpack Compose dan Navigation Compose dalam pengembangan aplikasi Android. Teknologi-teknologi modern ini memungkinkan pengembang untuk membuat antarmuka pengguna yang dinamis dan responsif dengan menggunakan paradigma deklaratif.

## Dependensi

Untuk menggunakan Jetpack Compose dan Navigation Compose, tambahkan dependensi berikut ke dalam file `build.gradle` (Module: app):

```gradle
dependencies {
  implementation("androidx.compose.material:material:1.6.8")
  val nav_version = "2.7.7"
  implementation("androidx.navigation:navigation-compose:$nav_version")
}
```

Pastikan untuk mengganti nav_version dengan versi terbaru dari Navigation Compose. Anda dapat memeriksa versi terbaru dan dokumentasi di [android.compose/navigation](https://developer.android.com/develop/ui/compose/navigation).

<hr>

### Navigate with arguments
Secara default, semua argumen diuraikan sebagai string. Parameter arguments dari composable() menerima daftar objek NamedNavArgument. Anda dapat dengan cepat membuat NamedNavArgument menggunakan metode `navArgument()`, lalu menentukan type yang tepat:

<h5>mengirim argumen</h5>

```kotlin
navController.navigate("route/argumen") {
    popUpTo(navController.graph.startDestinationId) 
    launchSingleTop = true
}
```
<h5>Menerima Argumen</h5>

```kotlin
fun Screen(argumen:String?){
  Text(text = "Profile $argumen")  
}
```
<hr>

### Nested Navigation

Dengan menentukan `NavController` pada level yang lebih tinggi dalam hierarki composable, Anda dapat menghubungkan Navigasi dengan komponen lain seperti komponen navigasi bawah. Dengan melakukan hal ini, Anda dapat menavigasi dengan memilih ikon di panel bawah.

Untuk menggunakan komponen `BottomNavigation` dan `BottomNavigationItem`, tambahkan dependensi `androidx.compose.material` ke aplikasi Android Anda.

<h5>Main Activity</h5>

```kotlin
val navController = rememberNavController() // Membuat controller untuk navigasi
    Scaffold(
        bottomBar = { BottomBar(navController = navController) } // Menambahkan BottomBar di bagian bawah
    ) {
        // NavHost untuk mengatur navigasi antar halaman
        NavHost(
            navController = navController, // Controller untuk navigasi
            startDestination = "home", // Halaman awal adalah "home"
            modifier = Modifier.padding(it) // Menambahkan padding untuk konten agar tidak tertutup oleh topBar dan bottomBar
        ) {
            composable("home") { HomeScreen(navController) } // Merender Halaman "home"
            composable("setting") { HomeScreen(navController) } // Merender Halaman "setting"
        }
    }
}

```

<h5>Membuat Komponent BottomBar</h5>

```kotlin
fun BottomBar(navController: NavController) {
    // Mendapatkan context saat ini
    val context = LocalContext.current

    // Mendefinisikan item navigasi bawah
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, "Home"),
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
            val isSelected = currentRoute == item.route

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
                          navController.navigate(item.route) {
                              popUpTo(navController.graph.startDestinationId) // Mengatur popUpTo untuk mencegah tumpukan back stack bertambah
                              launchSingleTop = true // Meluncurkan hanya satu instance dari destinasi
                          }
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primaryContainer, // Warna konten yang dipilih
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary, // Warna konten yang tidak dipilih
                modifier = if(isSelected)Modifier.padding(vertical = 8.dp) else Modifier.padding(top = 14.dp) // Padding untuk item
            )
        }
    }
}
```

<h3>Terima Kasih</h3>
Terima kasih telah menggunakan contoh implementasi Jetpack Compose dan Navigation Compose ini. Semoga bermanfaat bagi proyek Anda!

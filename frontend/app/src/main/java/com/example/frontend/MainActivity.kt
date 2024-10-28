package com.example.frontend

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.frontend.domain.SearchViewModel
import com.example.frontend.ui.screens.ResultScreen
import com.example.frontend.ui.screens.SearchScreen
import com.example.frontend.ui.theme.FrontendTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FusedLocationProviderClient の初期化
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // パーミッションリクエストのセットアップ
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // パーミッションが許可されたら位置情報を取得
                getCurrentLocation { latitude, longitude ->
                    setupNavigation(latitude, longitude)
                }
            } else {
                // 許可されなかった場合は東京駅の緯度経度を渡す
                val tokyoLat = 35.681236
                val tokyoLng = 139.767125
                setupNavigation(tokyoLat, tokyoLng)
            }
        }

        // FINE_LOCATION のパーミッションをリクエスト
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // 位置情報を取得する関数
    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(onLocationReceived: (Double, Double) -> Unit) {
        val locationResult: Task<Location> = fusedLocationClient.lastLocation
        locationResult.addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                val location: Location = task.result
                onLocationReceived(location.latitude, location.longitude)
            } else {
                // 位置情報を取得できない場合の処理
                Log.e("Location", "位置情報が取得できません")
            }
        }
    }

    // ナビゲート設定
    private fun setupNavigation(latitude: Double, longitude: Double) {
        setContent {
            FrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val searchViewModel: SearchViewModel = viewModel()
                    NavHost(navController = navController, startDestination = "search") {
                        composable("search") {
                            SearchScreen(navController, searchViewModel, latitude, longitude)
                        }
                        composable(
                            route = "result/{selectedTabIndex}/{latitude}/{longitude}/{genre}/{range}/{largeServiceArea}/{largeArea}/{middleArea}/{smallArea}",
                            arguments = listOf(
                                navArgument("selectedTabIndex") { type = NavType.StringType; nullable = true },
                                navArgument("latitude") { type = NavType.StringType; nullable = true },
                                navArgument("longitude") { type = NavType.StringType; nullable = true },
                                navArgument("genre") { type = NavType.StringType; nullable = true },
                                navArgument("range") { type = NavType.StringType; nullable = true },
                                navArgument("largeServiceArea") { type = NavType.StringType; nullable = true },
                                navArgument("largeArea") { type = NavType.StringType; nullable = true },
                                navArgument("middleArea") { type = NavType.StringType; nullable = true },
                                navArgument("smallArea") { type = NavType.StringType; nullable = true }
                            )
                        ) { backStackEntry ->
                            val selectedTabIndex = backStackEntry.arguments?.getString("selectedTabIndex")
                            val lat = backStackEntry.arguments?.getString("latitude")
                            val lon = backStackEntry.arguments?.getString("longitude")
                            val genre = backStackEntry.arguments?.getString("genre")
                            val range = backStackEntry.arguments?.getString("range")
                            val largeServiceArea = backStackEntry.arguments?.getString("largeServiceArea")
                            val largeArea = backStackEntry.arguments?.getString("largeArea")
                            val middleArea = backStackEntry.arguments?.getString("middleArea")
                            val smallArea = backStackEntry.arguments?.getString("smallArea")

                            ResultScreen(
                                navController = navController,
                                searchViewModel = searchViewModel,
                                selectedTabIndex = selectedTabIndex,
                                latitude = lat,
                                longitude = lon,
                                genre = genre,
                                range = range,
                                largeServiceArea = largeServiceArea,
                                largeArea = largeArea,
                                middleArea = middleArea,
                                smallArea = smallArea
                            )
                        }
                    }
                }
            }
        }
    }
}
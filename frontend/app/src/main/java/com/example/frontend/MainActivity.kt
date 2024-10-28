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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                Log.d("isGranted", "isGranted")
                // パーミッションが許可されたら位置情報を取得
                getCurrentLocation { latitude, longitude ->
                    setupNavigation(latitude, longitude)
                }
            } else {
                // 許可されなかった場合は東京駅の緯度経度を渡す
                val tokyoLat = 35.681236
                val tokyoLng = 139.767125
                Log.d("isGranted", "not isGranted")
                setupNavigation(tokyoLat, tokyoLng)
            }
        }

        // FINE_LOCATION のパーミッションをリクエスト
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

//        setContent {
//            FrontendTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//                    val searchViewModel: SearchViewModel = viewModel()
//                    NavHost(navController = navController, startDestination = "search") {
//                        composable("search") { SearchScreen(navController, searchViewModel) }
//                        composable("result") { ResultScreen(navController, searchViewModel) }
//                    }
//                }
//            }
//        }
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
//                onLocationReceived(35.681236, 139.767125)
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
                        composable("result") {
                            ResultScreen(navController, searchViewModel)
                        }
                    }
                }
            }
        }
    }
}
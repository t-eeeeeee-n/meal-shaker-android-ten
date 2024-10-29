package com.example.frontend.ui.components

import androidx.navigation.NavController
import com.example.frontend.domain.SearchViewModel

fun performSearchAndNavigate(
    selectedTabIndex: Int,
    latitude: String?,
    longitude: String?,
    genre: String?,
    range: String?,
    largeServiceArea: String?,
    largeArea: String?,
    middleArea: String?,
    smallArea: String?,
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    if (selectedTabIndex == 0) {
        // Location-based search
        searchViewModel.searchByLocation(latitude ?: "", longitude ?: "", genre, range)
        navController.navigate("result/0/$latitude/$longitude/$genre/$range/null/null/null/null"){
            popUpTo("search") { inclusive = false }
        }
    } else {
        // Area-based search
        searchViewModel.searchByArea(largeServiceArea ?: "", largeArea, middleArea, smallArea, genre)
        navController.navigate("result/$selectedTabIndex/null/null/$genre/null/$largeServiceArea/$largeArea/$middleArea/$smallArea"){
            popUpTo("search") { inclusive = false }
        }
    }
}
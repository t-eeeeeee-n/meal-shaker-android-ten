package com.example.frontend.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend.data.models.Shop
import com.example.frontend.domain.SearchViewModel
import com.example.frontend.ui.components.ReSearchButton
import com.example.frontend.ui.components.SearchButton
import com.example.frontend.ui.components.ShopItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
    selectedTabIndex: String?,
    latitude: String?,
    longitude: String?,
    genre: String?,
    range: String?,
    largeServiceArea: String?,
    largeArea: String?,
    middleArea: String?,
    smallArea: String?
) {
    val searchResponse by searchViewModel.searchResult.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(searchResponse.shop) { shop ->
                        ShopItem(shop)
                    }
                }
                ReSearchButton(onClick = {
                    if (selectedTabIndex?.toInt() == 0) {
                        searchViewModel.searchByLocation(
                            latitude.toString(),
                            longitude.toString(), genre, range
                        )
                        navController.navigate(
                            "result/$selectedTabIndex/$latitude/$longitude/$genre/$range/null/null/null/null"
                        )
                    } else {
                        searchViewModel.searchByArea(
                            largeServiceArea ?: "",
                            largeArea,
                            middleArea,
                            smallArea,
                            genre
                        )
                        navController.navigate(
                            "result/$selectedTabIndex/null/null/$largeServiceArea/$largeArea/$middleArea/$smallArea/$genre"
                        )
                    }
                })
            }
        }
    )
}
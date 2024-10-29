package com.example.frontend.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend.domain.SearchViewModel
import com.example.frontend.ui.components.ReSearchButton
import com.example.frontend.ui.components.ShopItem
import com.example.frontend.ui.components.performSearchAndNavigate

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
    val isLoading by searchViewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack("search", false) }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { },
            )
        },
        content = { paddingValues ->

            // ローディング中にスピナーを表示
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
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
                        performSearchAndNavigate(
                            selectedTabIndex?.toInt() ?: 0,
                            latitude,
                            longitude,
                            genre,
                            range,
                            largeServiceArea,
                            largeArea,
                            middleArea,
                            smallArea,
                            searchViewModel,
                            navController
                        )
                    })
                }
            }
        }
    )
}
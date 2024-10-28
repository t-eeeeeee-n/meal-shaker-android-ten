package com.example.frontend.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.frontend.data.models.Shop
import com.example.frontend.domain.SearchViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val searchResponse by searchViewModel.searchResult.collectAsState()

    Column {
        Button(onClick = { navController.navigate("search") }) {
            Text(text = "HOME")
        }

        Text(text = "検索結果 (${searchResponse.shop.size} 件)")
        LazyColumn {
            items(searchResponse.shop) { shop ->
                Text(text = "店舗名: ${shop.name}")
                ShopItem(shop)
            }
        }
    }
}

@Composable
fun ShopItem(shop: Shop) {
    Log.d("ShopItem", "Rendering shop: ${shop.name}")
    Column {
        Text(text = "店名: ${shop.name}")
        Text(text = "住所: ${shop.address}")
        Text(text = "ジャンル: ${shop.genre.name}")
        Text(text = "予算: ${shop.budget.name}")
        Text(text = "アクセス: ${shop.access}")
    }
}
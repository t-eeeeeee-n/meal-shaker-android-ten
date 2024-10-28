package com.example.frontend.data.models

data class SearchResponse(
    val apiVersion: String = "",
    val resultsAvailable: Int = 0,
    val resultsReturned: String = "",
    val resultsStart: Int = 0,
    val shop: List<Shop> = emptyList()
)

package com.example.frontend.data.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("api_version") val apiVersion: String = "",
    @SerializedName("results_available") val resultsAvailable: Int = 0,
    @SerializedName("results_returned") val resultsReturned: String = "",
    @SerializedName("results_start") val resultsStart: Int = 0,
    @SerializedName("shop") val shop: List<Shop> = emptyList()
)

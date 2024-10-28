package com.example.frontend.data.repository

import com.example.frontend.data.api.RetrofitInstance
import com.example.frontend.data.models.SearchResponse

class SearchRepository {
    suspend fun searchByLocation(latitude: String, longitude: String, genre: String?, range: String?): SearchResponse {
        return RetrofitInstance.api.searchByLocation(latitude, longitude, genre, range)
    }
    suspend fun searchByArea(largeServiceArea: String, largeArea: String?, middleArea: String?, smallArea: String?, genre: String?): SearchResponse {
        return RetrofitInstance.api.searchByArea(largeServiceArea, largeArea, middleArea, smallArea, genre)
    }
}
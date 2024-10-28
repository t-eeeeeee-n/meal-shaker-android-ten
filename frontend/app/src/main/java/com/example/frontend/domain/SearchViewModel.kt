package com.example.frontend.domain

import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.models.SearchResponse
import com.example.frontend.data.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel(private val repository: SearchRepository = SearchRepository()) : ViewModel() {
    private val _searchResult = MutableStateFlow(SearchResponse())
    val searchResult: StateFlow<SearchResponse> = _searchResult

    fun searchByLocation(latitude: String, longitude: String, genre: String?, range: String?) {
        viewModelScope.launch {
            try {
                val response = repository.searchByLocation(latitude, longitude, genre, range)
                Log.d("SearchVM", "searchResult updated with ${response.shop.size} shops") // 更新の確認
                _searchResult.value = response
            } catch (e: IOException) {
                Log.e("SearchVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("SearchVM", "Server error", e)
            } catch (e: Exception) {
                Log.e("SearchVM", "Error occurred", e)
            }
        }
    }

    fun searchByArea(
        largeServiceArea: String, largeArea: String?,
        middleArea: String?, smallArea: String?, genre: String?
    ) {
        viewModelScope.launch {
            try {
                val response = repository.searchByArea(largeServiceArea, largeArea, middleArea, smallArea, genre)
                Log.d("SearchVM", "searchResult updated with ${response.shop.size} shops") // 更新の確認
                _searchResult.value = response
            } catch (e: IOException) {
                Log.e("SearchVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("SearchVM", "Server error", e)
            } catch (e: Exception) {
                Log.e("SearchVM", "Error occurred", e)
            }
        }
    }
}
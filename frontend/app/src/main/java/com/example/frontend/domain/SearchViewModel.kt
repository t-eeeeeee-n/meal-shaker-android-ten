package com.example.frontend.domain

import android.net.http.HttpException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    var selectedTabIndex = mutableIntStateOf(0)
        private set

    fun searchByLocation(latitude: String, longitude: String, genre: String?, range: String?) {
        Log.d("searchByLocation",  "${latitude}, ${longitude}, ${genre}, $range")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.searchByLocation(latitude, longitude, genre, range)
                _searchResult.value = response
            } catch (e: IOException) {
                Log.e("SearchVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("SearchVM", "Server error", e)
            } catch (e: Exception) {
                Log.e("SearchVM", "Error occurred", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchByArea(
        largeServiceArea: String, largeArea: String?,
        middleArea: String?, smallArea: String?, genre: String?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.searchByArea(largeServiceArea, largeArea, middleArea, smallArea, genre)
                _searchResult.value = response
            } catch (e: IOException) {
                Log.e("SearchVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("SearchVM", "Server error", e)
            } catch (e: Exception) {
                Log.e("SearchVM", "Error occurred", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
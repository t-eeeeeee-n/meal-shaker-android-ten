package com.example.frontend.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.models.Area
import com.example.frontend.data.repository.MiddleAreaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MiddleAreaViewModel(repository: MiddleAreaRepository = MiddleAreaRepository()): BaseSelectViewModel<Area>(repository, autoFetch = false) {
    fun onSelect(area: Area?) {
        if (area != null) {
            fetchOptions(area.code)
        } else {
            clearOptions()
        }
    }

    private fun fetchOptions(largeAreaCode: String) {
        viewModelScope.launch {
            try {
                Log.d("MiddleAreaVM", "Fetching genres...")
                selectOptionsFlow.value = repository.getOptions(largeAreaCode)
            } catch (e: IOException) {
                Log.e("MiddleAreaVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("MiddleAreaVM", "Server error", e)
            }
        }
    }

    fun clearOptions() {
        selectOptionsFlow.value = listOf()
    }
}
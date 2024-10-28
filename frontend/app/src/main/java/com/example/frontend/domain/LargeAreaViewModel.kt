package com.example.frontend.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.models.Area
import com.example.frontend.data.repository.LargeAreaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class LargeAreaViewModel(repository: LargeAreaRepository = LargeAreaRepository()): BaseSelectViewModel<Area>(repository, autoFetch = false) {
    fun onSelect(area: Area?) {
        if (area != null) {
            fetchOptions(area.code)
        } else {
            clearOptions()
        }
    }

    private fun fetchOptions(regionCode: String) {
        viewModelScope.launch {
            try {
                selectOptionsFlow.value = repository.getOptions(regionCode)
            } catch (e: IOException) {
                Log.e("LargeAreaVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("LargeAreaVM", "Server error", e)
            }
        }
    }

    private fun clearOptions() {
        selectOptionsFlow.value = listOf()
    }
}
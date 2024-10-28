package com.example.frontend.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.models.Area
import com.example.frontend.data.repository.SmallAreaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SmallAreaViewModel(repository: SmallAreaRepository = SmallAreaRepository()): BaseSelectViewModel<Area>(repository, autoFetch = false) {
    fun onSelect(area: Area?) {
        if (area != null) {
            fetchOptions(area.code)
        } else {
            clearOptions()
        }
    }

    private fun fetchOptions(smallAreaCode: String) {
        viewModelScope.launch {
            try {
                selectOptionsFlow.value = repository.getOptions(smallAreaCode)
            } catch (e: IOException) {
                Log.e("SmallAreaVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("SmallAreaVM", "Server error", e)
            }
        }
    }

    fun clearOptions() {
        selectOptionsFlow.value = listOf()
    }
}
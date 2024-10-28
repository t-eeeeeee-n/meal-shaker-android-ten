package com.example.frontend.domain

import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend.data.repository.SelectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

abstract class BaseSelectViewModel<T>(
    protected val repository: SelectRepository<T>,
    autoFetch: Boolean = true
) : ViewModel() {
    private val _selectedOption = MutableStateFlow<T?>(null)
    val selectedOption: StateFlow<T?> = _selectedOption

    protected val selectOptionsFlow = MutableStateFlow<List<T>>(emptyList())
    val selectOptions: StateFlow<List<T>> = selectOptionsFlow

    init {
        if (autoFetch) {
            fetchOptions()
        }
    }

    fun setOption(option: T?) {
        _selectedOption.value = option
    }

    private fun fetchOptions() {
        viewModelScope.launch {
            try {
                selectOptionsFlow.value = repository.getOptions()
            } catch (e: IOException) {
                Log.e("BaseSelectVM", "Network error", e)
            } catch (e: HttpException) {
                Log.e("BaseSelectVM", "Server error", e)
            }
        }
    }
}
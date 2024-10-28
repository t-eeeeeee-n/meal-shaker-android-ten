package com.example.frontend.data.repository

import com.example.frontend.data.api.RetrofitInstance
import com.example.frontend.data.models.Genre

class GenreRepository: SelectRepository<Genre> {
    override suspend fun getOptions(): List<Genre> {
        return RetrofitInstance.api.getGenres()
    }
}
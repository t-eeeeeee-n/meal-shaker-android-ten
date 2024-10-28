package com.example.frontend.domain

import com.example.frontend.data.models.Genre
import com.example.frontend.data.repository.GenreRepository

class GenreViewModel(repository: GenreRepository = GenreRepository()) : BaseSelectViewModel<Genre>(repository)
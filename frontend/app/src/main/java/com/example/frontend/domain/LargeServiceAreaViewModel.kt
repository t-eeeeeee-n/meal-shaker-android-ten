package com.example.frontend.domain

import com.example.frontend.data.models.Area
import com.example.frontend.data.repository.LargeServiceAreaRepository

class LargeServiceAreaViewModel(repository: LargeServiceAreaRepository = LargeServiceAreaRepository()) : BaseSelectViewModel<Area>(repository)
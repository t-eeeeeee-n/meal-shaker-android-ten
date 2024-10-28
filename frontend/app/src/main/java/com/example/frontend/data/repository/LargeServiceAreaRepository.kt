package com.example.frontend.data.repository

import com.example.frontend.data.api.RetrofitInstance
import com.example.frontend.data.models.Area

class LargeServiceAreaRepository: SelectRepository<Area> {
    override suspend fun getOptions(): List<Area> {
        return RetrofitInstance.api.getLargeServiceAreas()
    }
}
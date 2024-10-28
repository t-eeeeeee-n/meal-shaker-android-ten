package com.example.frontend.data.repository

import com.example.frontend.data.api.RetrofitInstance
import com.example.frontend.data.models.Area

class SmallAreaRepository: SelectRepository<Area> {
    override suspend fun getOptions(code : String): List<Area> {
        return RetrofitInstance.api.getSmallAreas(code)
    }
}
package com.example.frontend.data.repository

import com.example.frontend.data.models.Range
import com.example.frontend.utils.Constants

class RangeRepository: SelectRepository<Range> {
    override suspend fun getOptions(): List<Range> {
        return Constants.rangeOptions
    }
}
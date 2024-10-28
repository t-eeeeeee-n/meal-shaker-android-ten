package com.example.frontend.domain

import com.example.frontend.data.models.Range
import com.example.frontend.data.repository.RangeRepository

class RangeViewModel(repository: RangeRepository = RangeRepository()) : BaseSelectViewModel<Range>(repository)
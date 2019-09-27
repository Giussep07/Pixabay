package com.giussepr.pixabay.network

import androidx.paging.PagedList
import com.giussepr.pixabay.domain.PixabayImage

data class PixabayResponse(
    val totalHits: Int,
    val hits: List<PixabayImage>,
    val total: Int
)
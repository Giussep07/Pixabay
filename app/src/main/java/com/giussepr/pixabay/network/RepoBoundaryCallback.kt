package com.giussepr.pixabay.network

import androidx.paging.PagedList

class RepoBoundaryCallback(private val service: PixabayApi) :
    PagedList.BoundaryCallback<PixabayApi>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PixabayApi) {
        super.onItemAtEndLoaded(itemAtEnd)
    }
}
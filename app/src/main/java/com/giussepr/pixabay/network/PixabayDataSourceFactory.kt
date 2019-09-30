package com.giussepr.pixabay.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.giussepr.pixabay.domain.PixabayImage

class PixabayDataSourceFactory(private val pixabayService: PixabayApi) :
    DataSource.Factory<Int, PixabayImage>() {

    val pixabayDataSourceLiveData = MutableLiveData<PixabayDataSource>()

    override fun create(): DataSource<Int, PixabayImage> {
        val pixabayDataSource = PixabayDataSource(pixabayService)
        pixabayDataSourceLiveData.postValue(pixabayDataSource)
        return pixabayDataSource
    }
}
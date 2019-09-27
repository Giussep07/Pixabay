package com.giussepr.pixabay.ui.popular

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.giussepr.pixabay.domain.PixabayImage
import com.giussepr.pixabay.network.PixabayRetrofit
import com.giussepr.pixabay.network.RepoBoundaryCallback
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

class PopularImagesViewModel : ViewModel() {
    private val _images = MutableLiveData<List<PixabayImage>>()
    val images: LiveData<List<PixabayImage>>
        get() = _images

    init {
        getPixabayImages()
    }

    private fun getPixabayImages() {
        viewModelScope.launch {
            val getPixabayImages = PixabayRetrofit.retrofitService.getPixabayImages(page = 1)
            try {
                val dataSourceFactory = getPixabayImages.await()
                _images.value = dataSourceFactory.hits
            } catch (ex: Exception) {
                _images.value = null
            }
        }
    }
}

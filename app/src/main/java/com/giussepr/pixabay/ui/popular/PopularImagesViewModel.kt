package com.giussepr.pixabay.ui.popular

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.giussepr.pixabay.domain.PixabayImage
import com.giussepr.pixabay.network.*
import kotlinx.coroutines.launch

class PopularImagesViewModel : ViewModel() {

    private val pixabayApi = PixabayRetrofit.retrofitService
    private val perPage = 20
    private val pixabayDataSourceFactory: PixabayDataSourceFactory

    var images: LiveData<PagedList<PixabayImage>>

    init {
        pixabayDataSourceFactory = PixabayDataSourceFactory(pixabayApi)
        val config = PagedList.Config.Builder()
            .setPageSize(perPage)
            .setInitialLoadSizeHint(perPage)
            .setEnablePlaceholders(false)
            .build()

        images = LivePagedListBuilder(pixabayDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<PixabayDataSource, State>(
        pixabayDataSourceFactory.pixabayDataSourceLiveData, PixabayDataSource::state
    )

    fun imagesIsEmpty(): Boolean {
        return images.value?.isEmpty() ?: true
    }
}

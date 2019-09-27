package com.giussepr.pixabay.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.giussepr.pixabay.domain.PixabayImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class State {
    DONE, LOADING, ERROR
}

class PixabayDataSource(private val pixabayApi: PixabayApi) :
    PageKeyedDataSource<Int, PixabayImage>() {

    var state: MutableLiveData<State> = MutableLiveData()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PixabayImage>
    ) {
        updateState(State.LOADING)
        coroutineScope.launch {
            val getPixabayImages = pixabayApi.getPixabayImages(page = 1)
            try {
                val pixabayImages = getPixabayImages.await()
                updateState(State.DONE)
                callback.onResult(pixabayImages.hits, null, 2)

            } catch (ex: Exception) {
                updateState(State.ERROR)
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PixabayImage>) {
        updateState(State.LOADING)
        coroutineScope.launch {
            val getPixabayImages = pixabayApi.getPixabayImages(page = params.key)
            try {
                val pixabayImages = getPixabayImages.await()
                updateState(State.DONE)
                callback.onResult(pixabayImages.hits, params.key + 1)

            } catch (ex: Exception) {
                updateState(State.ERROR)
            }

        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PixabayImage>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}
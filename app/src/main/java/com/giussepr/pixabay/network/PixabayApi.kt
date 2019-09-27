package com.giussepr.pixabay.network

import androidx.paging.DataSource
import com.giussepr.pixabay.domain.PixabayImage
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET(".")
    fun getPixabayImages(
        @Query("key") key: String = "13767610-9159db4b48af8f5b9a755d84a",
        @Query("page") page: Int
    ):
            Deferred<PixabayResponse>
}
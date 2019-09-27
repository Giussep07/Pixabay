package com.giussepr.pixabay.domain

import com.squareup.moshi.Json

data class PixabayImage(
    val id: Int,
    val largeImageURL: String,
    @Json(name = "webformatHeight")
    val webFormatHeight: Int,
    @Json(name = "webformatWidth")
    val webFormatWidth: Int,
    val likes: Int,
    val imageWidth: Int,
    val imageHeight: Int,
    @Json(name = "user_id")
    val userId: Int,
    val views: Int,
    val comments: Int,
    val pageURL: String,
    @Json(name = "webformatURL")
    val webFormatURL: String,
    val type: String,
    val tags: String,
    val downloads: Int,
    val user: String,
    val favorites: Int,
    val imageSize: Int,
    val previewHeight: Int,
    val previewWidth: Int,
    val userImageURL: String,
    val previewURL: String
)
package com.giussepr.pixabay

import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.giussepr.pixabay.domain.PixabayImage
import com.giussepr.pixabay.ui.popular.adapter.ImageAdapter

@BindingAdapter("bindImageFromUrl")
fun bindImageFromUrl(imageView: ImageView, url: String?) {
    url?.let {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("bindListImages")
fun binListImages(recyclerView: RecyclerView, images: PagedList<PixabayImage>?) {
    val adapter = recyclerView.adapter as ImageAdapter
    adapter.submitList(images)
}

@BindingAdapter("bindTextCapitalized")
fun bindTextCapitalized(textView: TextView, text: String){
    textView.text = text.split(' ').joinToString(" ") { it.capitalize() }
}
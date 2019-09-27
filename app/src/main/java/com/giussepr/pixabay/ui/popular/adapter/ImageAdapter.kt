package com.giussepr.pixabay.ui.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giussepr.pixabay.databinding.GridItemViewBinding
import com.giussepr.pixabay.domain.PixabayImage

class ImageAdapter : ListAdapter<PixabayImage, ImageAdapter.PixabayImageViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<PixabayImage>() {
        override fun areItemsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class PixabayImageViewHolder(private val binding: GridItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pixabayImage: PixabayImage) {
            binding.pixabayImage = pixabayImage
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayImageViewHolder {
        return PixabayImageViewHolder(GridItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PixabayImageViewHolder, position: Int) {
        val pixabayImage = getItem(position)
        if (pixabayImage != null) {
            holder.bind(pixabayImage)
        }
    }
}
package com.giussepr.pixabay.ui.popular

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.giussepr.pixabay.R
import com.giussepr.pixabay.databinding.GridItemViewBinding
import com.giussepr.pixabay.databinding.PopularImagesFragmentBinding
import com.giussepr.pixabay.network.State
import com.giussepr.pixabay.ui.popular.adapter.ImageAdapter

class PopularImagesFragment : Fragment() {

    private lateinit var viewModel: PopularImagesViewModel
    private lateinit var binding: PopularImagesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PopularImagesFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(PopularImagesViewModel::class.java)

        binding.viewModel = viewModel
        val adapter = ImageAdapter()
        binding.popularsRecycler.adapter = adapter

        viewModel.getState().observe(this, Observer {
            binding.progressBar.visibility =
                if (viewModel.imagesIsEmpty() && it == State.LOADING) View.VISIBLE else View.GONE
        })

        return binding.root
    }

}

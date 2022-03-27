package com.shamlou.search.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shamlou.search.databinding.ItemSearchLoadingBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderViewHolder {
        return LoaderViewHolder(
            ItemSearchLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * view holder class for footer loader and error state handling
     */

    inner class LoaderViewHolder(private val binding: ItemSearchLoadingBinding) :
        RecyclerView.ViewHolder(binding.root), LifecycleOwner {

        private val lifecycleRegistry = LifecycleRegistry(this)

        init {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }

        override fun getLifecycle() = lifecycleRegistry

        fun bind(item: LoadState) {
            binding.apply {
                lifecycleOwner = this@LoaderViewHolder
                setVariable(1, item)
                executePendingBindings()
                if (loadState is LoadState.Loading) {
                    mlLoader.transitionToEnd()
                } else {
                    mlLoader.transitionToStart()
                }
                btnRetry.setOnClickListener {
                    retry()
                }
            }

        }
    }
}
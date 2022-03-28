package com.shamlou.search.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.search.databinding.ItemSearchBinding

class LoaderSearchItemsAdapter :
    PagingDataAdapter<ResponseItemsDomain, LoaderSearchItemsAdapter.SearchImageViewHolder>(
        REPO_COMPARATOR
    ) {
    var onItemClicked: ((item: ResponseItemsDomain) -> Unit)? = null

    companion object {
        private val REPO_COMPARATOR =
            object : DiffUtil.ItemCallback<ResponseItemsDomain>() {
                override fun areItemsTheSame(
                    oldItem: ResponseItemsDomain,
                    newItem: ResponseItemsDomain
                ) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ResponseItemsDomain,
                    newItem: ResponseItemsDomain
                ) =
                    oldItem.id == newItem.id
            }
    }

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoaderSearchItemsAdapter.SearchImageViewHolder {
        return SearchImageViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * view holder class for search result item
     */
    inner class SearchImageViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root), LifecycleOwner {

        private val lifecycleRegistry = LifecycleRegistry(this)

        init {
            lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }

        override fun getLifecycle() = lifecycleRegistry

        fun bind(item: ResponseItemsDomain?) {
            binding.apply {
                lifecycleOwner = this@SearchImageViewHolder
                binding.item = item
                executePendingBindings()
                root.apply {
                    setOnClickListener {
                        item?.let {

                            onItemClicked?.invoke(it)
                        }
                    }
                }
            }

        }
    }
}
package com.shamlou.data.utility

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.shamlou.domain.model.search.ResponseItemsDomain

class NoopListCallback : ListUpdateCallback {
  override fun onChanged(position: Int, count: Int, payload: Any?) {}
  override fun onMoved(fromPosition: Int, toPosition: Int) {}
  override fun onInserted(position: Int, count: Int) {}
  override fun onRemoved(position: Int, count: Int) {}
}

class MyDiffCallback : DiffUtil.ItemCallback<ResponseItemsDomain>() {
  override fun areItemsTheSame(oldItem: ResponseItemsDomain, newItem: ResponseItemsDomain): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: ResponseItemsDomain, newItem: ResponseItemsDomain): Boolean {
    return oldItem == newItem
  }
}
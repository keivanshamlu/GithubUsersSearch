package com.shamlou.data.utility

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.shamlou.data.model.search.ResponseItemsRemote

class NoopListCallback : ListUpdateCallback {
  override fun onChanged(position: Int, count: Int, payload: Any?) {}
  override fun onMoved(fromPosition: Int, toPosition: Int) {}
  override fun onInserted(position: Int, count: Int) {}
  override fun onRemoved(position: Int, count: Int) {}
}

class MyDiffCallback : DiffUtil.ItemCallback<ResponseItemsRemote>() {
  override fun areItemsTheSame(oldItem: ResponseItemsRemote, newItem: ResponseItemsRemote): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: ResponseItemsRemote, newItem: ResponseItemsRemote): Boolean {
    return oldItem == newItem
  }
}
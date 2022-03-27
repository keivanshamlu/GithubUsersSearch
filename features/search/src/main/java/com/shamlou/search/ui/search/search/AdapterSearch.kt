package com.shamlou.search.ui.search.search

import com.shamlou.bases_android.recyclerview.adapter.AdapterBase
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.search.R

class AdapterSearch : AdapterBase<ResponseItemsDomain>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
) {

    override fun getItemViewType(position: Int): Int = R.layout.item_search
}
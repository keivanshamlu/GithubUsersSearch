package com.shamlou.data.model.search

import com.google.gson.annotations.SerializedName


data class ResponseSearchRemote(

    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: List<ResponseItemsRemote> = arrayListOf()

)
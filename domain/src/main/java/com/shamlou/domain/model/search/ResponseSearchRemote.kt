package com.shamlou.domain.model.search


data class ResponseSearchDomain(

    var totalCount: Int,
    var incompleteResults: Boolean,
    var items: List<ResponseItemsDomain> = arrayListOf()
)
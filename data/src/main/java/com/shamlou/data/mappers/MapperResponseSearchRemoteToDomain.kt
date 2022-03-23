package com.shamlou.data.mappers

import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.model.search.ResponseSearchRemote
import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.search.ResponseSearchDomain
import javax.inject.Inject

class MapperResponseSearchRemoteToDomain @Inject constructor() : Mapper<ResponseSearchRemote, ResponseSearchDomain> {
    override fun map(first: ResponseSearchRemote): ResponseSearchDomain = first.run {
        ResponseSearchDomain(
            totalCount?:-1, incompleteResults?:false,
            items = items.map {
                ResponseItemsDomain(
                    it.name?:"",
                    it.displayName?:"",
                    it.shortDescription?:"",
                    it.description?:"",
                    it.createdBy?:"",
                    it.released?:"",
                    it.createdAt?:"",
                    it.updatedAt?:"",
                    it.featured?:false,
                    it.curated?:false,
                    it.score?:0,
                )
            }
        )
    }
}
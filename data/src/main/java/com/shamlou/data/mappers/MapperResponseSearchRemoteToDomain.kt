package com.shamlou.data.mappers

import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.model.search.ResponseItemsRemote
import com.shamlou.domain.model.search.ResponseItemsDomain
import javax.inject.Inject

/**
 * maps responseItemRemote to its corresponding domain type
 */
class MapperResponseSearchRemoteToDomain @Inject constructor() :
    Mapper<ResponseItemsRemote, ResponseItemsDomain> {
    override fun map(first: ResponseItemsRemote): ResponseItemsDomain = first.let {
        ResponseItemsDomain(
            it.login ?: "",
            it.id ?: -1,
            it.nodeId ?: "",
            it.avatarUrl ?: "",
            it.gravatarId ?: "",
            it.url ?: "",
            it.htmlUrl ?: "",
            it.followersUrl ?: "",
            it.subscriptionsUrl ?: "",
            it.organizationsUrl ?: "",
            it.reposUrl ?: "",
            it.receivedEventsUrl ?: "",
            it.type ?: "",
            it.score ?: 0,
            it.followingUrl ?: "",
            it.gistsUrl ?: "",
            it.starredUrl ?: "",
            it.eventsUrl ?: "",
            it.siteAdmin ?: false
        )
    }
}
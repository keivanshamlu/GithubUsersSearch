
package com.shamlou.data.mappers

import com.shamlou.bases_android.mapper.Mapper
import com.shamlou.data.model.userDetail.ResponseUserDetailRemote
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import javax.inject.Inject

class MapperResponseUserDetailsRemoteToDomain @Inject constructor() :
    Mapper<ResponseUserDetailRemote, ResponseUserDetailDomain> {

    override fun map(first: ResponseUserDetailRemote): ResponseUserDetailDomain = first.run {
        ResponseUserDetailDomain(
            login?:"",
            id?:-1,
            nodeId?:"",
            avatarUrl?:"",
            gravatarId?:"",
            url?:"",
            htmlUrl?:"",
            followersUrl?:"",
            followingUrl?:"",
            gistsUrl?:"",
            starredUrl?:"",
            subscriptionsUrl?:"",
            organizationsUrl?:"",
            reposUrl?:"",
            eventsUrl?:"",
            receivedEventsUrl?:"",
            type?:"",
            siteAdmin?:false,
            name?:"",
            company?:"",
            blog?:"",
            location?:"",
            email?:"",
            hireable?:"",
            bio?:"",
            twitterUsername?:"",
            publicRepos?:0,
            publicGists?:0,
            followers?:0,
            following?:0,
            createdAt?:"",
            updatedAt?:""
        )
    }

}
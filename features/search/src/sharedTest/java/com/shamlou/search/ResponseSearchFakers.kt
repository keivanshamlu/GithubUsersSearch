package com.shamlou.search

import com.shamlou.domain.model.search.ResponseItemsDomain
import com.shamlou.domain.model.userDetails.ResponseUserDetailDomain
import java.io.IOException

object ResponseSearchFakers {

    val exampleUserName = "keivanShamlu"
    private const val sampleErrorText = "this is an error"
    val sampleError = IOException(sampleErrorText)

    val validResponseUserDetailDomain = ResponseUserDetailDomain("keivanShamlu", 1131232, "nodeId", "avatarUrl", "gravatarId", "url", "htmlUrl", "followersUrl", "followingUrl", "gistsUrl", "starredUrl", "subscriptions_url","organizations_url", "repos_url", "events_url","received_events_url", "type", false, "name", "company", "blog", "location", "email", "hireable", "bio", "twitter_username", 123, 234, 456, 678, "created_at", "updated_at")

    val validResponseItemsDomain = ResponseItemsDomain("login", 123, "node_id", "avatar_url", "gravatar_id", "url", "html_url", "followers_url", "subscriptions_url", "organizations_url", "repos_url", "received_events_url", "type", 345, "following_url", "gists_url", "starred_url", "events_url", false)
    val validResponseItemsDomain2 = ResponseItemsDomain("login2", 123, "node_id2", "avatar_url2", "gravatar_id2", "url2", "html_url2", "followers_url2", "subscriptions_url2", "organizations_url2", "repos_url2", "received_events_url2", "type2", 3452, "following_url2", "gists_url2", "starred_url2", "events_url2", false)
    val validResponseItemsDomain3 = ResponseItemsDomain("login3", 123, "node_id3", "avatar_url3", "gravatar_id3", "url3", "html_url3", "followers_url3", "subscriptions_url3", "organizations_url3", "repos_url3", "received_events_url3", "type3", 3453, "following_url3", "gists_url3", "starred_url3", "events_url3", false)

    val itemsDomain = listOf(validResponseItemsDomain, validResponseItemsDomain2, validResponseItemsDomain3)
}
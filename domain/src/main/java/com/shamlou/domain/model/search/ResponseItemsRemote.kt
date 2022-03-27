package com.shamlou.domain.model.search


data class ResponseItemsDomain(

    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val receivedEventsUrl: String,
    val type: String,
    val score: Int,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val eventsUrl: String,
    val siteAdmin: Boolean
)
package com.shamlou.data.services

import com.shamlou.data.BuildConfig
import com.shamlou.data.model.search.ResponseSearchRemote
import com.shamlou.data.model.userDetail.ResponseUserDetailRemote
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    // base url already added
    // i added my personal access token since
    // github has limited request count on IPs
    @Headers(
        "accept:application/vnd.github.v3+json",
        BuildConfig.API_KEY,
    )
    @GET("/search/users")
    suspend fun searchGithubUser(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("q") query: String
    ): ResponseSearchRemote

    @Headers(
        "accept:application/vnd.github.v3+json",
        BuildConfig.API_KEY,
    )
    @GET("/users/{username}")
    suspend fun getUserDetails(
        @Path("username") userName: String
    ): ResponseUserDetailRemote
}

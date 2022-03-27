package com.shamlou.data.services

import com.shamlou.data.model.search.ResponseSearchRemote
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {

    // base url already added
    // i added my personal access token since
    // github has limited request count on IPs
    @Headers(
        "accept:application/vnd.github.v3+json",
        "Authorization: token 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",
    )
    @GET("/search/users")
    suspend fun searchGithubUser(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("q") query: String
    ): ResponseSearchRemote
}

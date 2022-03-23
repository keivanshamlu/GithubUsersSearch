package com.shamlou.data.services

import com.shamlou.data.model.search.ResponseSearchRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    //base url already added
    @GET("api/v2/pokemon-species")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ResponseSearchRemote
}

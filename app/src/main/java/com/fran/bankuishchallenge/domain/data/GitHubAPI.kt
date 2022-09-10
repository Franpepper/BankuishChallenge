package com.fran.bankuishchallenge.domain.data

import com.fran.bankuishchallenge.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<SearchResponse>
}
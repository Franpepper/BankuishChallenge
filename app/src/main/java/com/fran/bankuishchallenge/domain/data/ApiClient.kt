package com.fran.bankuishchallenge.domain.data

import com.fran.bankuishchallenge.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val api: GitHubAPI
){

    suspend fun getRepos(query: String) : Response<SearchResponse> {
        return api.getRepos(query, perPage, page)
    }

    companion object{
        const val perPage = 20
        const val page = 1
    }
}
package com.astrapay.jason_ajaib_test.client

import com.astrapay.jason_ajaib_test.client.dto.repos.ReposResponseItem
import com.astrapay.jason_ajaib_test.client.dto.search.SearchResponse
import com.astrapay.jason_ajaib_test.client.dto.user.UserDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    //Search User
    @GET("/search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<SearchResponse>

    //User Detail
    @GET("/users/{loginUserName}")
    suspend fun userDetail(
        @Path("loginUserName") userName: String
    ): Response<UserDetailResponse>

    //User Detail
    @GET("/users/{loginUserName}/repos")
    suspend fun reposList(
        @Path("loginUserName") userName: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<List<ReposResponseItem>>
}
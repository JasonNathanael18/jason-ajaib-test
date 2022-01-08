package com.astrapay.jason_ajaib_test.service

import com.astrapay.jason_ajaib_test.client.ApiClient
import com.astrapay.jason_ajaib_test.client.dto.search.SearchResponse
import com.astrapay.jason_ajaib_test.client.dto.user.UserDetailResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ConnectionService @Inject constructor(
    private val apiClient: ApiClient,
    private val dispatcher: CoroutineDispatcher? = null
) {

    private fun getDispatcher() = dispatcher ?: Dispatchers.IO

    suspend fun searchUser(
        query: String,
        page: Int
    ): Response<SearchResponse> {
        return withContext(getDispatcher()) {
            return@withContext apiClient.searchUser(
                query = query,
                perPage = 10,
                page = page
            )
        }
    }

    suspend fun userDetail(
        userName: String
    ): Response<UserDetailResponse> {
        return withContext(getDispatcher()) {
            return@withContext apiClient.userDetail(
                userName = userName
            )
        }
    }
}
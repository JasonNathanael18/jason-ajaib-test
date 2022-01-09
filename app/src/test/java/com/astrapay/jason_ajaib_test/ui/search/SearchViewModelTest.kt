package com.astrapay.jason_ajaib_test.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.astrapay.TestCoroutineRule
import com.astrapay.getOrWaitValue
import com.astrapay.jason_ajaib_test.client.dto.search.ItemsResult
import com.astrapay.jason_ajaib_test.client.dto.search.SearchResponse
import com.astrapay.jason_ajaib_test.client.dto.user.UserDetailResponse
import com.astrapay.jason_ajaib_test.helper.exception.ServerFailedException
import com.astrapay.jason_ajaib_test.service.ConnectionService
import com.google.common.truth.Truth
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest : TestCase() {
    @get:Rule
    var taskRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var connectionService: ConnectionService

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Test
    fun `requestUserSearch WHEN user is success THEN liveSearch has value`() = runBlockingTest {
        Mockito.doReturn(
            Response.success(
                SearchResponse(
                    items = listOf(
                        ItemsResult(id = 1)
                    )
                )
            )
        )
            .`when`(connectionService)
            .searchUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        Mockito.doReturn(
            Response.success(
                UserDetailResponse(
                    id = 1
                )
            )
        )
            .`when`(connectionService)
            .userDetail(Mockito.anyString())

        val viewModel = initViewModel()
        viewModel.requestSearch(
            "tester",
            true
        )

        val liveData = viewModel.liveSearch.getOrWaitValue()
        Truth.assertThat(liveData.content).isNotNull()
        Truth.assertThat(liveData.content!!).hasSize(1)
    }

    @Test
    fun `requestUserSearch WHEN user is not found THEN liveErrorEmptyList has value`() = runBlockingTest {
        Mockito.doReturn(
            Response.success(
                SearchResponse(
                    items = listOf()
                )
            )
        )
            .`when`(connectionService)
            .searchUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        val viewModel = initViewModel()
        viewModel.requestSearch(
            "tester",
            true
        )

        val liveData = viewModel.liveErrorEmptyList.getOrWaitValue()
        Truth.assertThat(liveData.content).isNotNull()
        Truth.assertThat(liveData.content!!).isTrue()
    }

    @Test
    fun `request WHEN request search User response is failure THEN liveError has value`() = runBlockingTest {

        val errorMessage = "something went wrong"
        Mockito.doAnswer {
            throw ServerFailedException(errorMessage)
        }.`when`(connectionService).searchUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        val viewModel = initViewModel()
        viewModel.requestSearch("test", true)

        val liveData = viewModel.liveError.getOrWaitValue()
        Truth.assertThat(liveData.message).isNotNull()
    }

    @Test
    fun `request WHEN request User response is failure THEN liveError has value`() = runBlockingTest {
        Mockito.doReturn(
            Response.success(
                SearchResponse(
                    items = listOf(
                        ItemsResult(id = 1)
                    )
                )
            )
        )
            .`when`(connectionService)
            .searchUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        val errorMessage = "something went wrong"
        Mockito.doAnswer {
            throw ServerFailedException(errorMessage)
        }.`when`(connectionService).userDetail(Mockito.anyString())

        val viewModel = initViewModel()
        viewModel.requestSearch("test", true)

        val liveData = viewModel.liveError.getOrWaitValue()
        Truth.assertThat(liveData.message).isNotNull()
    }

    private fun initViewModel() = SearchViewModel(
        savedStateHandle, connectionService
    )
}
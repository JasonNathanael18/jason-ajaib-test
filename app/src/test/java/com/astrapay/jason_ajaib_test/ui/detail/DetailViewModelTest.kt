package com.astrapay.jason_ajaib_test.ui.detail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.astrapay.TestCoroutineRule
import com.astrapay.getOrWaitValue
import com.astrapay.jason_ajaib_test.client.dto.repos.ReposResponseItem
import com.astrapay.jason_ajaib_test.helper.exception.ServerFailedException
import com.astrapay.jason_ajaib_test.service.ConnectionService
import com.google.common.truth.Truth
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
class DetailViewModelTest {
    @get:Rule
    var taskRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var connectionService: ConnectionService

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Mock
    lateinit var context: Context

    @Test
    fun `requestRepos WHEN response is success THEN liveReposList has value`() = runBlockingTest {
        Mockito.doReturn(
            Response.success(
                listOf(
                    ReposResponseItem(id = 1),
                    ReposResponseItem(id = 2)
                )

            )
        )
            .`when`(connectionService)
            .getRepos(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        val viewModel = initViewModel()
        viewModel.requestReposList(
            "tester"
        )

        val liveData = viewModel.liveReposList.getOrWaitValue()
        Truth.assertThat(liveData.content).isNotNull()
        Truth.assertThat(liveData.content!!).hasSize(2)
    }

    @Test
    fun `requestRepos WHEN response is failure THEN liveError has value`() = runBlockingTest {

        val errorMessage = "something went wrong"
        Mockito.doAnswer {
            throw ServerFailedException(errorMessage)
        }.`when`(connectionService).getRepos(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())

        val viewModel = initViewModel()
        viewModel.requestReposList("test")

        val liveData = viewModel.liveError.getOrWaitValue()
        Truth.assertThat(liveData.message).isNotNull()
    }

    private fun initViewModel() = DetailViewModel(
        savedStateHandle, connectionService
    )
}
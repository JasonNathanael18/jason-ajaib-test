package com.astrapay.jason_ajaib_test.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.astrapay.jason_ajaib_test.MainViewModel
import com.astrapay.jason_ajaib_test.helper.DefaultConstants
import com.astrapay.jason_ajaib_test.helper.data.EventData
import com.astrapay.jason_ajaib_test.service.ConnectionService
import com.astrapay.jason_ajaib_test.ui.detail.viewdata.RepositoriesViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val connectionService: ConnectionService
) : MainViewModel() {
    enum class SavedStateKey {
        NextPageToLoad
    }

    private val _liveReposList: MutableLiveData<EventData<List<RepositoriesViewData>>> by lazy { MutableLiveData<EventData<List<RepositoriesViewData>>>() }
    val liveReposList: LiveData<EventData<List<RepositoriesViewData>>> get() = _liveReposList

    private val _liveErrorEmptyList: MutableLiveData<EventData<Boolean>> by lazy { MutableLiveData<EventData<Boolean>>() }
    val liveErrorEmptyList: MutableLiveData<EventData<Boolean>> get() = _liveErrorEmptyList


    private var nextPageToLoad: Int =
        savedStateHandle.get(SavedStateKey.NextPageToLoad.name) ?: 1

    fun requestReposList(userName: String) {
        viewModelScope.launch(exceptionHandler) {

            val response = connectionService.getRepos(
                userName,
                nextPageToLoad,
                DefaultConstants.InfiniteScrollList.loadLimitPerRequest_10
            )

            if (nextPageToLoad == 1 && response.body()!!.isEmpty()) {
                _liveErrorEmptyList.value = EventData(content = true)
            } else {
                // map the response data
                val listViewData = response.body()!!.map {
                    RepositoriesViewData.from(it)
                }

                if (listViewData.isNotEmpty()) {
                    incrementNextPageToLoadByOne()
                }
                _liveReposList.value = EventData(content = listViewData)
            }
        }
    }

    private fun incrementNextPageToLoadByOne() {
        nextPageToLoad += 1
    }

}
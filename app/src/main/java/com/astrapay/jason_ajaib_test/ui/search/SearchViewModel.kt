package com.astrapay.jason_ajaib_test.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.astrapay.jason_ajaib_test.MainViewModel
import com.astrapay.jason_ajaib_test.helper.DefaultConstants
import com.astrapay.jason_ajaib_test.helper.data.EventData
import com.astrapay.jason_ajaib_test.service.ConnectionService
import com.astrapay.jason_ajaib_test.ui.search.viewdata.SearchViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val connectionService: ConnectionService
) : MainViewModel() {
    enum class SavedStateKey {
        NextPageToLoad
    }

    private var lastQuery = ""

    private val _liveSearch: MutableLiveData<EventData<List<SearchViewData>>> by lazy { MutableLiveData<EventData<List<SearchViewData>>>() }
    val liveSearch: LiveData<EventData<List<SearchViewData>>> get() = _liveSearch

    private val _liveSearchMore: MutableLiveData<EventData<List<SearchViewData>>> by lazy { MutableLiveData<EventData<List<SearchViewData>>>() }
    val liveSearchMore: LiveData<EventData<List<SearchViewData>>> get() = _liveSearchMore

    private var nextPageToLoad: Int = savedStateHandle.get(SavedStateKey.NextPageToLoad.name) ?: 1
        set(value) {
            savedStateHandle.set(SavedStateKey.NextPageToLoad.name, value)
            field = value
        }

    fun requestSearch(query: String, newQuery: Boolean) {
        if (newQuery) {resetNextPageToLoadToOne()}

        lastQuery = query
        viewModelScope.launch(exceptionHandler) {
            val response = connectionService.searchUser(
                query,
                nextPageToLoad,
                DefaultConstants.InfiniteScrollList.loadLimitPerRequest_10
            )

            // map the response data
            val listViewData = response.body()!!.items?.map {
                SearchViewData.from(it!!)
            }

            if (listViewData!!.isNotEmpty()) {
                incrementNextPageToLoadByOne()
            }

            _liveSearch.value = EventData(content = listViewData)
        }
    }

    fun requestMoreSearch() {
        viewModelScope.launch(exceptionHandler) {
            val response = connectionService.searchUser(
                lastQuery,
                nextPageToLoad,
                DefaultConstants.InfiniteScrollList.loadLimitPerRequest_10
            )

            // map the response data
            val listViewData = response.body()!!.items?.map {
                SearchViewData.from(it!!)
            }

            if (listViewData!!.isNotEmpty()) {
                incrementNextPageToLoadByOne()
            }

            _liveSearch.value = EventData(content = listViewData)
        }
    }

//    fun requestDetailUser(userId: String) {
//        viewModelScope.launch(exceptionHandler) {
//            val response = connectionService.userDetail(
//                userId
//            )
//
//            // map the response data
//            val listViewData = response.body()!!.data.map {
//                HomePromotionInfo.from(it)
//            }
//
//            if (listViewData.isNotEmpty()) {
//                incrementNextPageToLoadByOne()
//            }
//
//            _livePromotion.value = EventData(content = listViewData)
//        }
//    }

    private fun incrementNextPageToLoadByOne() {
        nextPageToLoad += 1
    }

    private fun resetNextPageToLoadToOne() {
        nextPageToLoad = 1
    }
}
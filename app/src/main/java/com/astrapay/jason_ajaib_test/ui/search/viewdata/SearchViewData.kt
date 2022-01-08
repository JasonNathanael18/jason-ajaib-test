package com.astrapay.jason_ajaib_test.ui.search.viewdata

import com.astrapay.jason_ajaib_test.client.dto.search.ItemsResult

data class SearchViewData(
    val userId: String?,
    val thumbnail: String?
){
    companion object {
        fun from(source: ItemsResult) = SearchViewData(
            userId = source.login ?: "",
            thumbnail = source.avatarUrl ?: ""
        )
    }
}

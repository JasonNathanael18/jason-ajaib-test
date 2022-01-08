package com.astrapay.jason_ajaib_test.ui.search.viewdata

import com.astrapay.jason_ajaib_test.client.dto.search.ItemsResult

class SearchResultTempViewData(
    val userId: String?
) {
    companion object {
        fun from(source: ItemsResult) = SearchResultTempViewData(
            userId = source.login ?: ""
        )
    }
}
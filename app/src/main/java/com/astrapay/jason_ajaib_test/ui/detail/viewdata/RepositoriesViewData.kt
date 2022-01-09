package com.astrapay.jason_ajaib_test.ui.detail.viewdata

import com.astrapay.jason_ajaib_test.client.dto.repos.ReposResponseItem

data class RepositoriesViewData(
    val thumbnail: String,
    val reposName: String,
    val reposDetail: String,
    val stars: Int,
    val updatedAt: String
){
    companion object {
        fun from(source: ReposResponseItem) = RepositoriesViewData(
            thumbnail = source.owner?.avatarUrl ?: "",
            reposName = source.name ?: "",
            reposDetail = source.description ?: "",
            stars = source.stargazersCount ?: 0,
            updatedAt = source.updatedAt ?: ""
        )
    }
}

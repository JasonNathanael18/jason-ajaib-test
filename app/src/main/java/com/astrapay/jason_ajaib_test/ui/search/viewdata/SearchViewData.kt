package com.astrapay.jason_ajaib_test.ui.search.viewdata

import com.astrapay.jason_ajaib_test.client.dto.user.UserDetailResponse

data class SearchViewData(
    val userId: String,
    val thumbnail: String,
    val name: String,
    val bio: String,
    val location: String,
    val email: String,
    val followers: Int,
    val following: Int
){
    companion object {
        fun from(source: UserDetailResponse) = SearchViewData(
            userId = source.login ?: "",
            thumbnail = source.avatarUrl ?: "",
            name = source.name ?: "",
            bio = source.bio ?: "",
            location = source.location ?: "",
            email = source.email ?: "",
            followers = source.followers ?: 0,
            following = source.following ?: 0
        )
    }
}

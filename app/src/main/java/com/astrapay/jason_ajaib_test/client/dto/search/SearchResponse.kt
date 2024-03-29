package com.astrapay.jason_ajaib_test.client.dto.search

import com.squareup.moshi.Json

data class SearchResponse(

    @field:Json(name = "total_count")
    val totalCount: Int? = null,

    @field:Json(name = "incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:Json(name = "items")
    val items: List<ItemsResult?>? = null
)

data class ItemsResult(

    @field:Json(name = "gists_url")
    val gistsUrl: String? = null,

    @field:Json(name = "repos_url")
    val reposUrl: String? = null,

    @field:Json(name = "following_url")
    val followingUrl: String? = null,

    @field:Json(name = "starred_url")
    val starredUrl: String? = null,

    @field:Json(name = "login")
    val login: String? = null,

    @field:Json(name = "followers_url")
    val followersUrl: String? = null,

    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "subscriptions_url")
    val subscriptionsUrl: String? = null,

    @field:Json(name = "score")
    val score: Double? = null,

    @field:Json(name = "received_events_url")
    val receivedEventsUrl: String? = null,

    @field:Json(name = "avatar_url")
    val avatarUrl: String? = null,

    @field:Json(name = "events_url")
    val eventsUrl: String? = null,

    @field:Json(name = "html_url")
    val htmlUrl: String? = null,

    @field:Json(name = "site_admin")
    val siteAdmin: Boolean? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "gravatar_id")
    val gravatarId: String? = null,

    @field:Json(name = "node_id")
    val nodeId: String? = null,

    @field:Json(name = "organizations_url")
    val organizationsUrl: String? = null
)

package com.astrapay.jason_ajaib_test.client.dto.repos

import com.squareup.moshi.Json

data class ReposResponseItem(

    @field:Json(name = "allow_forking")
    val allowForking: Boolean? = null,

    @field:Json(name = "stargazers_count")
    val stargazersCount: Int? = null,

    @field:Json(name = "is_template")
    val isTemplate: Boolean? = null,

    @field:Json(name = "pushed_at")
    val pushedAt: String? = null,

    @field:Json(name = "subscription_url")
    val subscriptionUrl: String? = null,

    @field:Json(name = "language")
    val language: String? = null,

    @field:Json(name = "branches_url")
    val branchesUrl: String? = null,

    @field:Json(name = "issue_comment_url")
    val issueCommentUrl: String? = null,

    @field:Json(name = "labels_url")
    val labelsUrl: String? = null,

    @field:Json(name = "subscribers_url")
    val subscribersUrl: String? = null,

    @field:Json(name = "releases_url")
    val releasesUrl: String? = null,

    @field:Json(name = "svn_url")
    val svnUrl: String? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "forks")
    val forks: Int? = null,

    @field:Json(name = "archive_url")
    val archiveUrl: String? = null,

    @field:Json(name = "git_refs_url")
    val gitRefsUrl: String? = null,

    @field:Json(name = "forks_url")
    val forksUrl: String? = null,

    @field:Json(name = "visibility")
    val visibility: String? = null,

    @field:Json(name = "statuses_url")
    val statusesUrl: String? = null,

    @field:Json(name = "ssh_url")
    val sshUrl: String? = null,

    @field:Json(name = "license")
    val license: Any? = null,

    @field:Json(name = "full_name")
    val fullName: String? = null,

    @field:Json(name = "size")
    val size: Int? = null,

    @field:Json(name = "languages_url")
    val languagesUrl: String? = null,

    @field:Json(name = "html_url")
    val htmlUrl: String? = null,

    @field:Json(name = "collaborators_url")
    val collaboratorsUrl: String? = null,

    @field:Json(name = "clone_url")
    val cloneUrl: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "pulls_url")
    val pullsUrl: String? = null,

    @field:Json(name = "default_branch")
    val defaultBranch: String? = null,

    @field:Json(name = "hooks_url")
    val hooksUrl: String? = null,

    @field:Json(name = "trees_url")
    val treesUrl: String? = null,

    @field:Json(name = "tags_url")
    val tagsUrl: String? = null,

    @field:Json(name = "private")
    val jsonMemberPrivate: Boolean? = null,

    @field:Json(name = "contributors_url")
    val contributorsUrl: String? = null,

    @field:Json(name = "has_downloads")
    val hasDownloads: Boolean? = null,

    @field:Json(name = "notifications_url")
    val notificationsUrl: String? = null,

    @field:Json(name = "open_issues_count")
    val openIssuesCount: Int? = null,

    @field:Json(name = "description")
    val description: String? = null,

    @field:Json(name = "created_at")
    val createdAt: String? = null,

    @field:Json(name = "watchers")
    val watchers: Int? = null,

    @field:Json(name = "keys_url")
    val keysUrl: String? = null,

    @field:Json(name = "deployments_url")
    val deploymentsUrl: String? = null,

    @field:Json(name = "has_projects")
    val hasProjects: Boolean? = null,

    @field:Json(name = "archived")
    val archived: Boolean? = null,

    @field:Json(name = "has_wiki")
    val hasWiki: Boolean? = null,

    @field:Json(name = "updated_at")
    val updatedAt: String? = null,

    @field:Json(name = "comments_url")
    val commentsUrl: String? = null,

    @field:Json(name = "stargazers_url")
    val stargazersUrl: String? = null,

    @field:Json(name = "disabled")
    val disabled: Boolean? = null,

    @field:Json(name = "git_url")
    val gitUrl: String? = null,

    @field:Json(name = "has_pages")
    val hasPages: Boolean? = null,

    @field:Json(name = "owner")
    val owner: Owner? = null,

    @field:Json(name = "commits_url")
    val commitsUrl: String? = null,

    @field:Json(name = "compare_url")
    val compareUrl: String? = null,

    @field:Json(name = "git_commits_url")
    val gitCommitsUrl: String? = null,

    @field:Json(name = "topics")
    val topics: List<String?>? = null,

    @field:Json(name = "blobs_url")
    val blobsUrl: String? = null,

    @field:Json(name = "git_tags_url")
    val gitTagsUrl: String? = null,

    @field:Json(name = "merges_url")
    val mergesUrl: String? = null,

    @field:Json(name = "downloads_url")
    val downloadsUrl: String? = null,

    @field:Json(name = "has_issues")
    val hasIssues: Boolean? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "contents_url")
    val contentsUrl: String? = null,

    @field:Json(name = "mirror_url")
    val mirrorUrl: Any? = null,

    @field:Json(name = "milestones_url")
    val milestonesUrl: String? = null,

    @field:Json(name = "teams_url")
    val teamsUrl: String? = null,

    @field:Json(name = "fork")
    val fork: Boolean? = null,

    @field:Json(name = "issues_url")
    val issuesUrl: String? = null,

    @field:Json(name = "events_url")
    val eventsUrl: String? = null,

    @field:Json(name = "issue_events_url")
    val issueEventsUrl: String? = null,

    @field:Json(name = "assignees_url")
    val assigneesUrl: String? = null,

    @field:Json(name = "open_issues")
    val openIssues: Int? = null,

    @field:Json(name = "watchers_count")
    val watchersCount: Int? = null,

    @field:Json(name = "node_id")
    val nodeId: String? = null,

    @field:Json(name = "homepage")
    val homepage: String? = null,

    @field:Json(name = "forks_count")
    val forksCount: Int? = null
)

data class Owner(

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

data class License(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "spdx_id")
    val spdxId: String? = null,

    @field:Json(name = "key")
    val key: String? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "node_id")
    val nodeId: String? = null
)

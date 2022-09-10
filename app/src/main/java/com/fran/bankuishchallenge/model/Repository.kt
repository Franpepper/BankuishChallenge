package com.fran.bankuishchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Int,
    val name: String,
    val description: String,
    val owner: RepoOwner,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
): Parcelable

package com.fran.bankuishchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoOwner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("html_url")
    val url: String,
) : Parcelable

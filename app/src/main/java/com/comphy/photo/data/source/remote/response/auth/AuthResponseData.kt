package com.comphy.photo.data.source.remote.response.auth

import com.google.gson.annotations.SerializedName

data class AuthResponseData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("userId")
    val userId: Int? = null
)
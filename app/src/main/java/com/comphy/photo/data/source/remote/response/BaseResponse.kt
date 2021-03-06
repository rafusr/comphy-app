package com.comphy.photo.data.source.remote.response

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("Status")
    val status: String,

    @SerializedName("data")
    val data: JsonObject? = null,

    @SerializedName("message")
    val message: String
)

package com.nafi.quoteapp.data.model

import com.google.gson.annotations.SerializedName

data class Quote(
    var id: String,
    val quote: String,
    val anime: String,
    val character: String
)

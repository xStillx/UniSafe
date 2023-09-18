package com.example.unisafe.features.shoplists.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListResponse(
    @Json(name = "shop_list")
    val shopList: List<ItemRes>,
    val success: Boolean
)

@JsonClass(generateAdapter = true)
data class ItemRes(
    val created: String,
    val id: Int,
    val name: String
)
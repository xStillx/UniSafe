package com.example.unisafe.features.shoplists.domain.model


data class ShopList(
    val itemList: List<Item>,
    val success: Boolean
)

data class Item(
    val created: String,
    val id: Int,
    val name: String
)
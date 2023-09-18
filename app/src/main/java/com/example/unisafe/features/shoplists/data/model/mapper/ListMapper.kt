package com.example.unisafe.features.shoplists.data.model.mapper

import com.example.unisafe.features.shoplists.data.model.ListResponse
import com.example.unisafe.features.shoplists.domain.model.ShopList
import javax.inject.Inject

class ListMapper @Inject constructor(
    private val shopMapper: ShopMapper
) {

    fun map(listResponse: ListResponse) = ShopList(
        itemList = listResponse.shopList.map {
            shopMapper.map(it)
        },
        success = listResponse.success
    )
}
package com.example.unisafe.features.itemlist.data.model.mapper

import com.example.unisafe.features.itemlist.data.model.ProductListResponse
import com.example.unisafe.features.shoplists.data.model.mapper.ShopMapper
import com.example.unisafe.features.shoplists.domain.model.ShopList
import javax.inject.Inject

class ProductListMapper @Inject constructor(
    private val shopMapper: ShopMapper
) {

    fun map(listResponse: ProductListResponse) = ShopList(
        itemList = listResponse.itemList.map {
            shopMapper.map(it)
        },
        success = listResponse.success
    )
}
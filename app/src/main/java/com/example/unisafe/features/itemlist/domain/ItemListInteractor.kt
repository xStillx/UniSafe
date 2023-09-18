package com.example.unisafe.features.itemlist.domain

import com.example.unisafe.features.itemlist.data.ItemListNetWorkRepository
import com.example.unisafe.utils.safeRequest
import javax.inject.Inject

class ItemListInteractor @Inject constructor(
    private val itemListNetWorkRepository: ItemListNetWorkRepository
) {

    suspend fun getProductList(id: String) = safeRequest {
        itemListNetWorkRepository.getProductList(id)
    }

    suspend fun createNewProduct(id: String, name: String, count: String) =
        safeRequest {
            itemListNetWorkRepository.createNewProduct(id, name, count)
        }

    suspend fun deleteProduct(id: String) = safeRequest {
        itemListNetWorkRepository.deleteProduct(id)
    }
}
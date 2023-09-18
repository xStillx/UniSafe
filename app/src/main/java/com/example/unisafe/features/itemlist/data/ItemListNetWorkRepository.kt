package com.example.unisafe.features.itemlist.data

import com.example.unisafe.features.itemlist.data.api.ProductApi
import com.example.unisafe.features.itemlist.data.model.mapper.CreateNewProductMapper
import com.example.unisafe.features.itemlist.data.model.mapper.DeleteProductMapper
import com.example.unisafe.features.itemlist.data.model.mapper.ProductListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemListNetWorkRepository @Inject constructor(
    private val api: ProductApi,
    private val listMapper: ProductListMapper,
    private val createNewProductMapper: CreateNewProductMapper,
    private val deleteProductMapper: DeleteProductMapper
) {

    suspend fun getProductList(id: String) = withContext(Dispatchers.IO) {
        api.getProductList(id).let {
            listMapper.map(it)
        }
    }

    suspend fun createNewProduct(id: String, name: String, count: String) =
        withContext(Dispatchers.IO) {
            api.createNewProduct(id = id, value = name, count = count).let {
                createNewProductMapper.map(it)
            }
        }

    suspend fun deleteProduct(id: String) = withContext(Dispatchers.IO) {
        api.deleteProduct(id).let {
            deleteProductMapper.map(it)
        }
    }
}
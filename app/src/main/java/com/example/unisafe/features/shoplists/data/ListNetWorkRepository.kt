package com.example.unisafe.features.shoplists.data

import com.example.unisafe.features.shoplists.data.api.ShopListApi
import com.example.unisafe.features.shoplists.data.model.mapper.CreateNewListMapper
import com.example.unisafe.features.shoplists.data.model.mapper.DeleteListMapper
import com.example.unisafe.features.shoplists.data.model.mapper.ListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListNetWorkRepository @Inject constructor(
    private val shopListApi: ShopListApi,
    private val listMapper: ListMapper,
    private val createNewListMapper: CreateNewListMapper,
    private val deleteListMapper: DeleteListMapper
) {

    suspend fun getShopLists() = withContext(Dispatchers.IO){
        shopListApi.getShopLists().let {
            listMapper.map(it)
        }
    }

    suspend fun createNewList(name: String) = withContext(Dispatchers.IO){
        shopListApi.createNewList(name).let {
            createNewListMapper.map(it)
        }
    }

    suspend fun deleteList(id: String) = withContext(Dispatchers.IO){
        shopListApi.deleteList(id).let {
            deleteListMapper.map(it)
        }
    }
}
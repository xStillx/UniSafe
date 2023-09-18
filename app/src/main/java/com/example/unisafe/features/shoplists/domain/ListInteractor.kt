package com.example.unisafe.features.shoplists.domain

import com.example.unisafe.features.shoplists.data.ListNetWorkRepository
import com.example.unisafe.utils.safeRequest
import javax.inject.Inject

class ListInteractor @Inject constructor(
    private val listNetWorkRepository: ListNetWorkRepository
){

    suspend fun getShopLists() = safeRequest {
        listNetWorkRepository.getShopLists()
    }

    suspend fun createNewList(name: String) = safeRequest {
        listNetWorkRepository.createNewList(name)
    }

    suspend fun deleteList(id: String) = safeRequest {
        listNetWorkRepository.deleteList(id)
    }
}
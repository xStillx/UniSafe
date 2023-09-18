package com.example.unisafe.features.shoplists.data.api

import com.example.unisafe.features.shoplists.data.model.CreateNewListResponse
import com.example.unisafe.features.shoplists.data.model.DeleteListResponse
import com.example.unisafe.features.shoplists.data.model.ListResponse
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopListApi {

    @POST("GetAllMyShopLists?key=W9HFYR")
    suspend fun getShopLists(): ListResponse

    @POST("CreateShoppingList?key=W9HFYR")
    suspend fun createNewList(@Query("name") name: String): CreateNewListResponse

    @POST("RemoveShoppingList?")
    suspend fun deleteList(@Query("list_id") id: String): DeleteListResponse
}
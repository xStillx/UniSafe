package com.example.unisafe.features.shoplists.di

import com.example.unisafe.features.shoplists.data.api.ShopListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ShopListsFeatureModule {

    @Provides
    fun provideShopListsApi(retrofit: Retrofit) = retrofit.create(
        ShopListApi::class.java
    )
}
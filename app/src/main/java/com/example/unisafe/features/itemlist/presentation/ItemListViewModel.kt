package com.example.unisafe.features.itemlist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unisafe.features.itemlist.domain.ItemListInteractor
import com.example.unisafe.features.itemlist.domain.model.DeleteProductAnswer
import com.example.unisafe.features.shoplists.domain.model.CreateNewListAnswer
import com.example.unisafe.features.shoplists.domain.model.ShopList
import com.example.unisafe.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemListInteractor: ItemListInteractor
) : ViewModel() {

    private val _productList = MutableLiveData<ViewState<ShopList>>()
    val productList get() = _productList.asLiveData()

    private val _createNewProductAnswer = MutableLiveData<ViewState<CreateNewListAnswer>>()
    val createNewProductAnswer get() = _createNewProductAnswer.asLiveData()

    private val _deleteProductAnswer = MutableLiveData<ViewState<DeleteProductAnswer>>()
    val deleteProductAnswer get() = _deleteProductAnswer.asLiveData()

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad get() = _isLoad.asLiveData()

    val showDialog = SingleLiveEvent<Unit>()

    fun getProductList(id: String) {
        _isLoad.value = true
        viewModelScope.launch {
            _productList.value = itemListInteractor.getProductList(id).asViewState()
            _isLoad.value = false
        }
    }

    fun createNewProduct(id: String, name: String, count: String) {
        viewModelScope.launch {
            _createNewProductAnswer.value =
                itemListInteractor.createNewProduct(id, name, count).asViewState()
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            _deleteProductAnswer.value = itemListInteractor.deleteProduct(id).asViewState()
        }
    }

    fun onAddProductClick() {
        showDialog.call()
    }
}
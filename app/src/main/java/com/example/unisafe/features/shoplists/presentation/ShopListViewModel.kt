package com.example.unisafe.features.shoplists.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unisafe.features.shoplists.domain.ListInteractor
import com.example.unisafe.features.shoplists.domain.model.CreateNewListAnswer
import com.example.unisafe.features.shoplists.domain.model.DeleteListAnswer
import com.example.unisafe.features.shoplists.domain.model.ShopList
import com.example.unisafe.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopListViewModel @Inject constructor(
    private val listInteractor: ListInteractor
): ViewModel(){

    private val _shopLists = MutableLiveData<ViewState<ShopList>>()
    val shopList get() = _shopLists.asLiveData()

    private val _createNewListAnswer = MutableLiveData<ViewState<CreateNewListAnswer>>()
    val createNewListAnswer get() = _createNewListAnswer.asLiveData()

    private val _deleteListAnswer = MutableLiveData<ViewState<DeleteListAnswer>>()
    val deleteListAnswer get() = _deleteListAnswer.asLiveData()

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad get() = _isLoad.asLiveData()

    val goToItemList = SingleLiveEvent<String>()
    val showDialog = SingleLiveEvent<Unit>()

    init {
        getShopList()
    }

    fun getShopList(){
        _isLoad.value = true
        viewModelScope.launch {
            _shopLists.value = listInteractor.getShopLists().asViewState()
            _isLoad.value = false
        }
    }

    fun createNewList(name: String){
        viewModelScope.launch {
            _createNewListAnswer.value = listInteractor.createNewList(name).asViewState()
        }
    }

    fun deleteList(id: String){
        viewModelScope.launch {
            _deleteListAnswer.value = listInteractor.deleteList(id).asViewState()
        }
    }

    fun onListClick(id: String){
        goToItemList.call(id)
    }
    fun onAddListClick(){
        showDialog.call()
    }
}
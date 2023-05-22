package com.hfad.shoplist.views.productList

import com.hfad.foundation.model.PendingResult
import com.hfad.shoplist.DetailListActionListener
import com.hfad.shoplist.model.Product
import com.hfad.foundation.navigator.Navigator
import com.hfad.shoplist.views.EditProduct.EditProductFragment
import com.hfad.foundation.views.BaseViewModel
import com.hfad.foundation.views.LiveResult
import com.hfad.foundation.views.MutableLiveResult
import com.hfad.shoplist.model.ProductRepository
import com.hfad.shoplist.views.productList.DetailListFragment.Screen
import kotlinx.coroutines.launch

class DetailListViewModel(
    private val repository: ProductRepository,
    private val navigator: Navigator,
    private val screen: Screen
): BaseViewModel(), DetailListActionListener {

    private val _detailList = MutableLiveResult<List<Product>>(PendingResult())
    val detailList: LiveResult<List<Product>> = _detailList

    fun loadDetailList(listId: Long) {
        into(_detailList) { repository.getByListId(listId) }
    }

    init {
        loadDetailList(screen.initialValue)
    }

    override fun onProductChecked(productId: Long) = viewModelScope.launch {
        try {
            repository.onProductChecked(productId)
        }
        catch (e: Exception) {

        }
    }

    override fun onEditProductValue(productId: Long) = viewModelScope.launch {
        try {
            navigator.launch(EditProductFragment.Screen(initialValue = productId))
        } catch (e: Exception) {

        }
    }

    override fun onDeleteProduct(product: Product) = viewModelScope.launch {
        TODO("Not yet implemented")
    }
}
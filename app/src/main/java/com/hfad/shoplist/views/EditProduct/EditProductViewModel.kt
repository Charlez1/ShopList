package com.hfad.shoplist.views.EditProduct

import androidx.lifecycle.MutableLiveData
import com.hfad.foundation.model.PendingResult
import com.hfad.shoplist.model.Product
import com.hfad.foundation.navigator.Navigator
import com.hfad.foundation.views.BaseViewModel
import com.hfad.foundation.views.MutableLiveResult
import com.hfad.shoplist.R
import com.hfad.shoplist.model.ProductRepository
import com.hfad.shoplist.views.EditProduct.EditProductFragment.Screen
import kotlinx.coroutines.launch

class EditProductViewModel(
    private val repository: ProductRepository,
    private val navigator: Navigator,
    private val screen: Screen
    ) : BaseViewModel() {

    private val _saveInProgress = MutableLiveData(false)

    private val _product = MutableLiveResult<Product>(PendingResult())
    val product: MutableLiveResult<Product> = _product

    fun loadProduct(productId: Long) {
        into(_product) { repository.getByProductId(productId)}
    }

    init {
        loadProduct(screen.initialValue)
    }

    fun onSaveProduct(name: String, amount: String, unit: String) = viewModelScope.launch {
        try {
            repository.onSaveProduct(screen.initialValue, name, amount, unit)
            navigator.goBack() }
        finally {
            _saveInProgress.postValue(false)
        }
    }

}
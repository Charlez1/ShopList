package com.hfad.shoplist.views.topList

import com.hfad.foundation.model.PendingResult
import com.hfad.shoplist.TopListActionListener
import com.hfad.shoplist.model.ProductList
import com.hfad.foundation.navigator.Navigator
import com.hfad.foundation.views.BaseViewModel
import com.hfad.foundation.views.LiveResult
import com.hfad.foundation.views.MutableLiveResult
import com.hfad.shoplist.model.ProductRepository
import com.hfad.shoplist.views.productList.DetailListFragment
import com.hfad.shoplist.views.topList.TopListFragment.Screen

class TopListViewModel(
    private val repository: ProductRepository,
    private val navigator: Navigator,
    private val screen: Screen
) : BaseViewModel(), TopListActionListener {

    private val _topList = MutableLiveResult<List<ProductList>>(PendingResult())
    val topList: LiveResult<List<ProductList>> = _topList


    fun loadTopList() {
        into(_topList) { repository.getTopList() }
    }

    init {
        loadTopList()
    }

    override fun onListDetails(productListId: Long) {
        val screen = DetailListFragment.Screen(initialValue = productListId)
        navigator.launch(screen)
    }

}
package com.hfad.shoplist.model

import com.hfad.foundation.model.Repository

interface ProductRepository: Repository {

    suspend fun onProductChecked(productId: Long)

    suspend fun getTopList(): List<ProductList>

    suspend fun getByListId(listId: Long): List<Product>

    suspend fun getByProductId(productId: Long): Product

    suspend fun onSaveProduct(productId: Long, name: String, amount: String, unit: String)

}
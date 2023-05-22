package com.hfad.shoplist.model

data class Product(
    val id: Long,
    var name: String,
    var amount: String,
    var unit: String,
    var isCompleted: Boolean,
    val parentId: Long
)

data class ProductList (
    val listId: Long,
    val listOfProduct: List<Product>,
    var nameOfList: String,
    var totalNumber: Long,
    var completedNumber: Long
    )
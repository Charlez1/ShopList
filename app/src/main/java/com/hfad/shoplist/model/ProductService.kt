package com.hfad.shoplist.model

import com.github.javafaker.Faker
import com.hfad.foundation.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

typealias TopListListener = (topList : List<ProductList>) -> Unit

class ProductService : ProductRepository {
    private var topList = mutableListOf<ProductList>()

    init {
        val faker = Faker.instance()
        var productList : ProductList
        var listOfProduct: List<Product>
        var i = 0
        while (i < 4) {
             listOfProduct = (1..15).map { Product(
                 id = it.toLong() + i * 15,
                 name = faker.food().fruit(),
                 amount = faker.number().numberBetween(1, 5).toString(),
                 unit = "kg",
                 isCompleted = if ((0..1).random() == 1) true else false,
                 parentId = i.toLong() + 1
            ) }.toMutableList()

            productList = ProductList(
                listId = i.toLong() + 1,
                listOfProduct = listOfProduct,
                nameOfList = faker.color().name(),
                totalNumber = listOfProduct.size.toLong(),
                completedNumber = getProductListCompletedNumber(listOfProduct),
            )
            topList.add(productList)
            i++
        }
    }

    fun getProductListCompletedNumber(listOfProduct: List<Product>) : Long {
        var competedNumber: Long = 0;
        listOfProduct.forEach {
            if (it.isCompleted) {
                competedNumber++
            }
        }
        return competedNumber
    }

    override suspend fun getTopList(): List<ProductList>  {
        delay(1000)
        return topList
    }

    override suspend fun getByListId(listId: Long): List<Product> = withContext(Dispatchers.IO) {
        delay(1000)
        var indexToReturn: Int = topList.indexOfFirst {
            it.listId == listId
        }
        return@withContext topList.get(indexToReturn).listOfProduct
    }

    override suspend fun getByProductId(productId: Long): Product {
        delay(1000)
        var product: Product = topList[0].listOfProduct[0]
        topList.forEach {
            it.listOfProduct.forEach {
                if (it.id == productId) product = it
            }
        }
        return product
    }

    override suspend fun onProductChecked(productId: Long) {
        val product = getByProductId(productId)
        var listOfProduct: ProductList? = null
        topList.forEach {
            if (it.listId == product?.parentId) {
                listOfProduct = it
            }
        }
        var productToChecked: Product? = null
        listOfProduct?.listOfProduct?.forEach{
            if (it.id == product?.id) productToChecked = it
        }
        productToChecked?.isCompleted = !productToChecked?.isCompleted!!
        var completedNumber = listOfProduct?.completedNumber
        if (completedNumber != null) {
            if (productToChecked?.isCompleted == true) listOfProduct?.completedNumber = completedNumber + 1
            else listOfProduct?.completedNumber = completedNumber - 1
        }
    }

    override suspend fun onSaveProduct(productId: Long, name: String, amount: String, unit: String) {
        delay(1000)
        val product = getByProductId(productId)
        product?.name = name
        product?.amount = amount
        product?.unit = unit
    }


//    fun deleteTopListItem(productList: ProductList) {
//        val indexToDelete: Int = topList.indexOfFirst {
//            it == productList
//        }
//        if (indexToDelete != -1) {
//            topList.removeAt(indexToDelete)
//            topListListeners.forEach {it.invoke(topList)}
//        }
//    }
}
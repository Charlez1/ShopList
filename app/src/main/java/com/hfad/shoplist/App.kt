package com.hfad.shoplist

import android.app.Application
import com.hfad.foundation.BaseApplication
import com.hfad.foundation.model.Repository
import com.hfad.shoplist.model.ProductRepository
import com.hfad.shoplist.model.ProductService

class App : Application(), BaseApplication {

    override val repositories: List<Repository> = listOf(
        ProductService()
    )
}

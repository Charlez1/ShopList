package com.hfad.foundation.navigator

import com.hfad.foundation.views.BaseScreen

interface Navigator {

    fun launch(screen: BaseScreen)

    fun goBack()

}
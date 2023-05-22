package com.hfad.foundation.navigator

import com.hfad.foundation.utils.MainThreadExecutor
import com.hfad.foundation.utils.ResourceActions
import com.hfad.foundation.views.BaseScreen
import java.util.concurrent.Executor

class IntermediateNavigator(
    executor: Executor = MainThreadExecutor()
) : Navigator {

    private val targetNavigator = ResourceActions<Navigator>(executor)

    override fun launch(screen: BaseScreen) {
        targetNavigator.invoke {
            it.launch(screen)
        }
    }

    override fun goBack() = targetNavigator {
        it.goBack()
    }

    fun setTarget(navigator: Navigator?) {
        targetNavigator.resource = navigator
    }

    fun clear() {
        targetNavigator.clear()
    }
}
package com.hfad.foundation

import androidx.lifecycle.ViewModel
import com.hfad.foundation.navigator.IntermediateNavigator
import com.hfad.foundation.navigator.Navigator

class ActivityScopeViewModel(
    val navigator: IntermediateNavigator
) : ViewModel(), Navigator by navigator {

    override fun onCleared() {
        super.onCleared()
        navigator.clear()
    }

}
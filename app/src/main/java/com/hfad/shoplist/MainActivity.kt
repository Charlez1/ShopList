package com.hfad.shoplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.foundation.ActivityScopeViewModel
import com.hfad.foundation.navigator.IntermediateNavigator
import com.hfad.foundation.navigator.StackFragmentNavigator
import com.hfad.foundation.utils.viewModelCreator
import com.hfad.foundation.views.FragmentsHolder
import com.hfad.shoplist.views.topList.TopListFragment

class MainActivity : AppCompatActivity(), FragmentsHolder {

    private lateinit var navigator: StackFragmentNavigator

    // делегируем свойство перекладывая инициализацию на функцию viewModelCreator
    private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(navigator = IntermediateNavigator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator = StackFragmentNavigator(
            activity = this,
            containerId = R.id.fragmentContainer,
            defaultTitle = getString(R.string.app_name),
            initialScreenCreator = { TopListFragment.Screen() } //передаем скрин с помощью которого создаем фрагмент
        )
        navigator.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        navigator.onDestroy()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        activityViewModel.navigator.setTarget(null)
    }

    override fun notifyScreenUpdates() {
        navigator.notifyScreenUpdates()
    }

    override fun getActivityScopeViewModel(): ActivityScopeViewModel {
        return activityViewModel
    }
}
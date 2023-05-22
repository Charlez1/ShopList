package com.hfad.foundation.utils

import com.hfad.shoplist.MainActivity
import java.util.concurrent.Executor

typealias ResourceAction<T> = (T) -> Unit

/**
 * This class executes actions only when activity is assigned to [mainActivity] field.
 * See setup logic and usage example in [MainNavigator] and [MainActivity]
 */
class ResourceActions<T> (
    private val executor: Executor
) {

    /**
     * Assign activity in [MainActivity.onResume] and assign NULL in [MainActivity.onPause]
     */

    var resource: T? = null
        set(newValue) {
            field = newValue
            if (newValue != null) {
                actions.forEach { action ->
                    executor.execute {
                        action(newValue)
                    }
                }
                actions.clear()
            }
        }

    private val actions = mutableListOf<ResourceAction<T>>()

    /**
     * Invoke operator allows using this class like this:
     *
     * ```
     * val runActionSafely = MainActivityActions()
     * fun doSomeActivityDependentLogic() = runActionSafely { activity ->
     *   // do navigation stuffs here
     * }
     * ```
     */
    operator fun invoke(action: ResourceAction<T>) {
        val resource = this.resource
        if (resource == null) {
            actions += action
        } else {
            executor.execute {
                action(resource)
            }
        }
    }

    /**
     * Call this method in navigator's [MainNavigator.onCleared]
     */
    fun clear() {
        actions.clear()
    }

}
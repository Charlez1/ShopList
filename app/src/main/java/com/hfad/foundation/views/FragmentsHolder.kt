package com.hfad.foundation.views

import com.hfad.foundation.ActivityScopeViewModel

interface FragmentsHolder {
    /**
     * Вызывается когда представления activity должны быть перерисованы
     */
    fun notifyScreenUpdates()

    /**
     * Get the current implementations of dependencies from activity VM scope.
     * Получить текущие реализации зависимостей из activity VM scope

     */
    fun getActivityScopeViewModel(): ActivityScopeViewModel

}
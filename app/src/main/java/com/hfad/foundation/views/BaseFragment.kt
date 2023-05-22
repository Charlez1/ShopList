package com.hfad.foundation.views

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.foundation.model.ErrorResult
import com.hfad.foundation.model.PendingResult
import com.hfad.foundation.model.SuccessResult
import com.hfad.foundation.model.Result

abstract class BaseFragment : BottomSheetDialogFragment() {

    abstract val viewModel: BaseViewModel

    fun <T> renderResult(root: ViewGroup, result: Result<T>,
                         onPending: () -> Unit,
                         onError: (Exception) -> Unit,
                         onSuccess: (T) -> Unit) {

        root.children.forEach { it.visibility = View.GONE }
        when (result) {
            is SuccessResult -> onSuccess(result.data)
            is ErrorResult -> onError(result.exception)
            is PendingResult -> onPending()
        }

    }
}
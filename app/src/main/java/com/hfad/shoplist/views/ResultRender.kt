package com.hfad.shoplist.views

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.hfad.foundation.views.BaseFragment
import com.hfad.shoplist.databinding.PartResultBinding
import com.hfad.foundation.model.Result
import com.hfad.shoplist.R


fun <T> BaseFragment.renderSimpleResult(root: ViewGroup, result: Result<T>, onSuccess: (T) -> Unit) {
    val binding = PartResultBinding.bind(root)

    renderResult(
        root = root,
        result = result,
        onPending = {
            binding.progressBar.visibility = View.VISIBLE
        },
        onError = {
            binding.errorContainer.visibility = View.VISIBLE
        },
        onSuccess = { successData ->
            root.children
                .filter { it.id != R.id.progressBar && it.id != R.id.errorContainer }
                .forEach { it.visibility = View.VISIBLE }
            onSuccess(successData)
        }
    )
}

package com.hfad.shoplist.views.EditProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.hfad.shoplist.databinding.FragmentEditProductBinding
import com.hfad.shoplist.model.Product
import com.hfad.foundation.views.BaseFragment
import com.hfad.foundation.views.BaseScreen
import com.hfad.foundation.views.screenViewModel
import com.hfad.shoplist.views.renderSimpleResult

class EditProductFragment : BaseFragment() {

    class Screen(
        val initialValue: Long
    ) : BaseScreen

    override val viewModel by screenViewModel<EditProductViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentEditProductBinding.inflate(inflater, container, false)

        viewModel.product.observe(viewLifecycleOwner, Observer { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = {
                    product -> setDialogValues(binding, product)
                }
            )
        })

        binding.fab.setOnClickListener {
            viewModel.onSaveProduct(
                binding.editProductName.text.toString(),
                binding.editProductAmount.text.toString(),
                binding.editProductUnit.text.toString()
            )
        }

        binding.addButton.setOnClickListener {
            var value: Long = getAmount(binding.editProductAmount.text.toString())
            if (value < Long.MAX_VALUE) binding.editProductAmount.setText((value + 1).toString())
        }

        binding.removeButton.setOnClickListener {
            var value: Long = getAmount(binding.editProductAmount.text.toString())
            if (value > 1) binding.editProductAmount.setText((value - 1).toString())
            else binding.editProductAmount.setText("")
        }
        return binding.root
    }

    fun setDialogValues(binding: FragmentEditProductBinding, product: Product) {
        binding.editProductName.setText(product.name)
        if (product.amount == "0") binding.editProductAmount.setText("")
        else binding.editProductAmount.setText(product.amount)
        binding.editProductUnit.setText(product.unit)
    }

    fun getAmount(text: String): Long {
        return if (text == "") 0
        else text.toLong()
    }
}
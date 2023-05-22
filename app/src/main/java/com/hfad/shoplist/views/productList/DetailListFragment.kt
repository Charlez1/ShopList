package com.hfad.shoplist.views.productList

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.shoplist.DetailListAdapter
import com.hfad.shoplist.databinding.FragmentDetailListBinding
import com.hfad.foundation.views.BaseFragment
import com.hfad.foundation.views.BaseScreen
import com.hfad.foundation.views.screenViewModel
import com.hfad.shoplist.views.renderSimpleResult

class DetailListFragment : BaseFragment() {

    class Screen(
        val initialValue: Long
    ) : BaseScreen

    override val viewModel by screenViewModel<DetailListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailListBinding.inflate(inflater, container, false)
        val adapter = DetailListAdapter(viewModel)

        viewModel.detailList.observe(viewLifecycleOwner, Observer { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = {
                    adapter.detailList = it
                }
            )
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

}
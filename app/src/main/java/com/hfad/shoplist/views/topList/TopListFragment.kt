package com.hfad.shoplist.views.topList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.shoplist.TopListAdapter
import com.hfad.shoplist.databinding.FragmentTopListBinding
import com.hfad.foundation.views.BaseFragment
import com.hfad.foundation.views.BaseScreen
import com.hfad.foundation.views.screenViewModel
import com.hfad.shoplist.views.renderSimpleResult

class TopListFragment: BaseFragment() {

    class Screen: BaseScreen

    override val viewModel by screenViewModel<TopListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding = FragmentTopListBinding.inflate(inflater, container, false)
        var adapter = TopListAdapter(viewModel)

        viewModel.topList.observe(viewLifecycleOwner, Observer { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = {
                    adapter.topList = it
                }
            )
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        return binding.root
    }
}
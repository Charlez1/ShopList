package com.hfad.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hfad.shoplist.databinding.ItemTopListBinding
import com.hfad.shoplist.model.ProductList


interface TopListActionListener {

    fun onListDetails(productListId: Long)
}

class TopListAdapter(
    private val actionListener: TopListActionListener
) : RecyclerView.Adapter<TopListAdapter.TopListViewHolder>(), View.OnClickListener {

    var topList: List<ProductList> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(view: View) {
        val productList = (view.tag as ProductList)
        when (view.id) {
            R.id.clickableLayout -> {
                actionListener.onListDetails(productList.listId)
            }
            else -> {
//                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = topList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopListBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        return TopListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopListViewHolder, position: Int) {
        val productList = topList[position]
        holder.itemView.tag = productList
        with(holder.binding) {
            listName.text = productList.nameOfList
            numbersOfProduct.text = productList.completedNumber.toString() + "/" + productList.totalNumber.toString()
            val i = ((productList.completedNumber.toDouble() / productList.totalNumber.toString().toDouble()) * 100).toInt()
            progressBar.setProgress(i)
        }

    }

    class TopListViewHolder(
        val binding: ItemTopListBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

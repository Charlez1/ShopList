package com.hfad.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hfad.shoplist.databinding.ItemDetailListBinding
import com.hfad.shoplist.databinding.ItemTopListBinding
import com.hfad.shoplist.model.Product
import com.hfad.shoplist.model.ProductList
import kotlinx.coroutines.Job

interface DetailListActionListener {

    fun onProductChecked(productId: Long): Job

    fun onEditProductValue(productId: Long): Job

    fun onDeleteProduct(product: Product): Job
}

class DetailListAdapter(
        private val actionListener: DetailListActionListener
    ) : RecyclerView.Adapter<DetailListAdapter.DetailListViewHolder>(), View.OnClickListener {

    var detailList: List<Product> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }
//
//    override fun onCheckedChanged(checkBox: CompoundButton?, isChecked: Boolean) {
//        val product = (checkBox?.tag as Product)
//        val i = 0
//    }

    override fun onClick(view: View) {
        val product = (view.tag as Product)
        when (view.id) {
            R.id.checkbox -> {
                actionListener.onProductChecked(product.id)
            }
            else -> {
                actionListener.onEditProductValue(product.id)
            }
        }
    }


    override fun getItemCount(): Int = detailList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailListAdapter.DetailListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailListBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.checkbox.setOnClickListener(this)

        return DetailListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailListAdapter.DetailListViewHolder, position: Int) {
        val product = detailList[position]
        holder.itemView.tag = product
        with(holder.binding) {
            checkbox.isChecked = product.isCompleted
            checkbox.tag = product
            productName.text = product.name
            productAmount.text = product.amount.toString() + " " +  product.unit
            productCost.text = "fix later"
        }
    }


    class DetailListViewHolder(
        val binding: ItemDetailListBinding
    ) : RecyclerView.ViewHolder(binding.root)


}
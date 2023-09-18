package com.example.unisafe.features.itemlist.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unisafe.R
import com.example.unisafe.databinding.ItemProductBinding
import com.example.unisafe.features.shoplists.domain.model.Item

class ProductListAdapter(
    private val onDeleteClick: (String) -> Unit,
) : ListAdapter<Item, ProductListAdapter.ProductListViewHolder>(ShopListsDiffCallBack()) {

    class ProductListViewHolder(
        view: View,
        private val onDeleteClick: (String) -> Unit,
    ) : RecyclerView.ViewHolder(view) {

        private val viewBinding: ItemProductBinding by viewBinding(ItemProductBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            viewBinding.tvName.text = item.name
            viewBinding.tvCount.text = item.created
            itemView.setOnClickListener {
                onDeleteClick.invoke(item.id.toString())
            }
        }

    }

    class ShopListsDiffCallBack: DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name && oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val cellForItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductListViewHolder(cellForItem, onDeleteClick)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
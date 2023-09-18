package com.example.unisafe.features.shoplists.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unisafe.R
import com.example.unisafe.databinding.ItemShopListBinding
import com.example.unisafe.features.shoplists.domain.model.Item

class ShopListsAdapter(
    private val onDeleteClick: (String) -> Unit,
    private val onItemClick: (String) -> Unit
) : ListAdapter<Item, ShopListsAdapter.ShopListsViewHolder>(ShopListsDiffCallBack()) {

    class ShopListsViewHolder(
        view: View,
        private val onDeleteClick: (String) -> Unit,
        private val onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val viewBinding: ItemShopListBinding by viewBinding(ItemShopListBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            viewBinding.tvName.text = item.name
            viewBinding.tvCreated.text = "Создан: ${item.created}"
            viewBinding.btnDelete.setOnClickListener {
                onDeleteClick.invoke(item.id.toString())
            }
            itemView.setOnClickListener {
                onItemClick.invoke(item.id.toString())
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListsViewHolder {
        val cellForItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_list, parent, false)
        return ShopListsViewHolder(cellForItem, onDeleteClick, onItemClick)
    }

    override fun onBindViewHolder(holder: ShopListsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
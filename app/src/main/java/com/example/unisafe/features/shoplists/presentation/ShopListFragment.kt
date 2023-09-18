package com.example.unisafe.features.shoplists.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unisafe.R
import com.example.unisafe.databinding.FragmentShopListsBinding
import com.example.unisafe.features.shoplists.presentation.adapter.ShopListsAdapter
import com.example.unisafe.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopListFragment : Fragment(R.layout.fragment_shop_lists) {

    private val viewBinding: FragmentShopListsBinding by viewBinding(FragmentShopListsBinding::bind)
    private val viewModel: ShopListViewModel by viewModels()
    private val shopListsAdapter: ShopListsAdapter = ShopListsAdapter({
        viewModel.deleteList(it)
    }) {
        viewModel.onListClick(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() {
        viewBinding.rvShopLists.adapter = shopListsAdapter
        viewBinding.btnAddNewList.setOnClickListener {
            viewModel.onAddListClick()
        }
    }

    private fun observe() {
        viewModel.isLoad.observe(viewLifecycleOwner){
            viewBinding.pbProgress.isVisible = it
        }
        viewModel.showDialog.observe(viewLifecycleOwner){
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.item_dialog, null)
            val dialogBuilder = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()
            dialogBuilder.show()
            val btnOk = dialogBuilder.findViewById<AppCompatButton>(R.id.btn_ok)
            val btnCancel = dialogBuilder.findViewById<AppCompatButton>(R.id.btn_cancel)
            val dialogTitle = dialogBuilder.findViewById<TextView>(R.id.tv_dialog)
            val etName = dialogBuilder.findViewById<EditText>(R.id.et_name)
            val etCount = dialogBuilder.findViewById<EditText>(R.id.et_count)
            val tvCountTitle = dialogBuilder.findViewById<TextView>(R.id.tv_count)
            btnCancel.setOnClickListener {
                dialogBuilder.cancel()
            }
            etCount.isVisible = false
            tvCountTitle.isVisible = false
            dialogTitle.text = resources.getText(R.string.new_list)
            btnOk.setOnClickListener {
                viewModel.createNewList(etName.text.toString())
                dialogBuilder.cancel()
            }
        }
        viewModel.goToItemList.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_shopListFragment_to_itemListFragment, bundleOf(
                "id" to it
            ))
        }
        viewModel.shopList.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    Log.d("ShopListFragment", "observe: ${it.throwable}")
                }
                is ViewState.Show -> {
                    shopListsAdapter.submitList(it.data.itemList)
                }
            }
        }
        viewModel.deleteListAnswer.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Delete Answer Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Delete Answer Error", Toast.LENGTH_SHORT).show()
                    Log.d("ShopListFragment", "observe: ${it.throwable}")
                }
                is ViewState.Show -> {
                    if (it.data.success){
                        viewModel.getShopList()
                    }
                }
            }
        }
        viewModel.createNewListAnswer.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Create New List Answer Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Create New List Answer Error", Toast.LENGTH_SHORT).show()
                    Log.d("ShopListFragment", "observe: ${it.throwable}")
                }
                is ViewState.Show -> {
                    if (it.data.success){
                        viewModel.getShopList()
                    }
                }
            }
        }
    }
}
package com.example.unisafe.features.itemlist.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unisafe.R
import com.example.unisafe.databinding.FragmentItemListBinding
import com.example.unisafe.features.itemlist.presentation.adapter.ProductListAdapter
import com.example.unisafe.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment(R.layout.fragment_item_list){

    private val viewBinding: FragmentItemListBinding by viewBinding(FragmentItemListBinding::bind)
    private val viewModel: ItemListViewModel by viewModels()
    private val productListAdapter = ProductListAdapter {
        viewModel.deleteProduct(it)
    }
    lateinit var id: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }

    private fun initViews() {
        (arguments?.getString("id") as String).let {
            id = it
        }
        viewModel.getProductList(id)
        viewBinding.rvProducts.adapter = productListAdapter
        viewBinding.btnAddNewProduct.setOnClickListener {
            viewModel.onAddProductClick()
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
            btnCancel.setOnClickListener {
                dialogBuilder.cancel()
            }
            dialogTitle.text = resources.getText(R.string.add_product)
            btnOk.setOnClickListener {
                viewModel.createNewProduct(id, etName.text.toString(), etCount.text.toString())
                dialogBuilder.cancel()
            }
        }
        viewModel.productList.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    Log.d("ItemListFragment", "observe: ${it.throwable}")
                }
                is ViewState.Show -> {
                    productListAdapter.submitList(it.data.itemList)
                }
            }
        }
        viewModel.deleteProductAnswer.observe(viewLifecycleOwner){
            when(it){
                ViewState.Empty -> {
                    Toast.makeText(requireContext(), "Delete Answer Empty", Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(requireContext(), "Delete Answer Error", Toast.LENGTH_SHORT).show()
                    Log.d("ItemListFragment", "observe: ${it.throwable}")
                }
                is ViewState.Show -> {
                    if (it.data.success){
                        viewModel.getProductList(id)
                    }
                }
            }
        }
        viewModel.createNewProductAnswer.observe(viewLifecycleOwner){
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
                        viewModel.getProductList(id)
                    }
                }
            }
        }
    }
}
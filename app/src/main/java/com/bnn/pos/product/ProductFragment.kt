package com.bnn.pos.product

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bnn.pos.admindashboard.AdminDashboardActivity
import com.bnn.pos.databinding.FragmentProductBinding
import com.bnn.pos.model.ProductModel

class ProductFragment : Fragment() {

	/*
		companion object {
			fun newInstance() = ProductFragment()
		}
	*/
	var can: Int = 1 // user action, if 1 add, 2 edit
	private lateinit var productAdapter: ProductAdapter
	private var dialog: Dialog? = null
	private lateinit var data: ProductModel
	private lateinit var viewModel: ProductViewModel
	private lateinit var binding: FragmentProductBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
		binding = FragmentProductBinding.inflate(inflater, container, false)

		data = ProductModel()

		binding.productSearch.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

			}

			override fun afterTextChanged(s: Editable?) {
				data.outletName = s.toString()
//				productAdapter.setItem(listOf(data))
			}
		})


		productAdapter = ProductAdapter(object : ProductAdapter.ProductListener{
			override fun onClick(model: ProductModel) {
				val dialogItem = arrayOf<CharSequence>(
					"Edit", "Delete"
				)
				val dialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
				dialog.setItems(dialogItem) { dialogInterface, i ->
					when (i) {
						0 -> {
							can = 2
							val intent = Intent(requireActivity(), ProductEditActivity::class.java)
							intent.putExtra("can", can)
							intent.putExtra("id", model.id)
							intent.putExtra("name", model.outletName )
//							intent.putExtra("address", model.outletAddress )
//							intent.putExtra("name", model.outletName )
//							intent.putExtra("outletModel", Gson().toJson(model) )
							startActivity(intent)
						}
						1 -> {
							deleteData(model.id, model.outletName)
						}

					}
					Log.d("delete", "onClick: " + model.id)
				}
				dialog.show()
			}


		})

		binding.productList.adapter = productAdapter
		binding.productList.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
		binding.productList.setHasFixedSize(true)

		binding.productBtnAdd.setOnClickListener {
			can = 1
			val intent = Intent(requireActivity(), ProductEditActivity::class.java)
			intent.putExtra("can", can)
			startActivity(intent)
		}

		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getData()

		viewModel.productListLiveData.observe(requireActivity()) {
			if (it != null) {
				productAdapter.setItem(it)
			}
		}

	}


	private fun deleteData(id: Int?, name: String?) {
		var content = ProductModel()
		content.id = id
		content.outletName = name

		(requireActivity() as AdminDashboardActivity).showConfirmationDialog(
			"Warning",
			"You are going to Delete " + content.outletName,
			"Yes",
			"no",
			false,
			object : AdminDashboardActivity.AnyDialogListener {
				override fun onPrimaryClose() {

					(requireActivity() as AdminDashboardActivity).showInformationDialog(
						"Attention",
						"Deleted Data Successfully",
						null
					)
				}

				override fun onSecondaryClose() {
				}
			}
		)
	}
}
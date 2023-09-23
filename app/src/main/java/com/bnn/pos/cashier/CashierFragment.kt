package com.bnn.pos.cashier

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bnn.pos.admindashboard.AdminDashboardActivity
import com.bnn.pos.admindashboard.ProductAdapter
import com.bnn.pos.admindashboard.AdminDashboardViewModel
import com.bnn.pos.databinding.FragmentCashierBinding
import com.bnn.pos.model.OutletModel

class CashierFragment : Fragment() {

	/*companion object {
		fun newInstance() = CashierFragment()
	}*/


	private lateinit var viewModel: CashierViewModel
	private lateinit var dashboardViewModel: AdminDashboardViewModel
	private lateinit var productAdapter: ProductAdapter

	private lateinit var binding: FragmentCashierBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding = FragmentCashierBinding.inflate(inflater, container, false)
		dashboardViewModel= ViewModelProvider(requireActivity())[AdminDashboardViewModel::class.java]
		viewModel = ViewModelProvider(requireActivity())[CashierViewModel::class.java]

		productAdapter = ProductAdapter()

		binding.cashierListProduct.adapter = productAdapter
		binding.cashierListProduct.layoutManager = GridLayoutManager(requireActivity(), 4, GridLayoutManager.VERTICAL, false )
		binding.cashierListProduct.setHasFixedSize(true)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getData()

		Log.d("fragment", "onViewCreated: ${viewModel.cashierListLiveData}")

		viewModel.cashierListLiveData.observe(requireActivity()){
			if (it!=null) {
				productAdapter.setItem(it)
			}
		}
	}
	private fun deleteData(id: Int?, name: String?) {
		var content = OutletModel()
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


	override fun onDestroy() {
		super.onDestroy()
	}
}

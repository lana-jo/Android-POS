package com.bnn.pos.employee

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bnn.pos.admindashboard.AdminDashboardActivity
import com.bnn.pos.databinding.FragmentEmployeeBinding
import com.bnn.pos.model.UserModel
import com.bnn.pos.outlet.OutletEditActivity

class EmployeeFragment : Fragment() {

	/*companion object {
		fun newInstance() = EmployeeFragment()
	}
*/
	var can: Int = 1 // if 1 add, 2 edit
	private lateinit var viewModel: EmployeeViewModel
	private lateinit var employeeadapter: EmployeeAdapter
	private lateinit var data : UserModel

	private lateinit var binding: FragmentEmployeeBinding
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		binding = FragmentEmployeeBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
		data = UserModel()
		data.role = 2
		employeeadapter= EmployeeAdapter(object : EmployeeAdapter.EmployeeAdapterListener{
			override fun click(model: UserModel) {
				val dialogItem = arrayOf<CharSequence>(
					"Edit", "Delete"
				)
				val dialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
				dialog.setItems(dialogItem) { dialogInterface, i ->
					when (i) {
						0 -> {
							can = 2
							val intent = Intent(requireActivity(), EmployeeEditActivity::class.java)
							intent.putExtra("can", can)
							intent.putExtra("outlet_id", model.id)
							intent.putExtra("name", model.name )
							intent.putExtra("address", model.address )
//							intent.putExtra("name", model.outletName )
//							intent.putExtra("outletModel", Gson().toJson(model) )
							startActivity(intent)
						}
						1 -> {
							deleteData(model.id, model.name)
						}

					}
					Log.d("delete", "onClick: " + model.id)
				}
				dialog.show()
			}
		})

		binding.employeeBtnAdd.setOnClickListener {
			can= 1
			val intent = Intent(requireContext(), EmployeeEditActivity::class.java)
			intent.putExtra("can", can)
			startActivity(intent)
		}

		binding.employeeList.adapter = employeeadapter
		binding.employeeList.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
		binding.employeeList.setHasFixedSize(true)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.getData()
		viewModel.employeeListLiveData.observe(requireActivity()){
			if (it!=null){
				employeeadapter.setItem(it)
			}
		}
	}


	private fun deleteData(id: Int?, name: String?) {
		var content = UserModel()
		content.id = id
		content.name = name

		(requireActivity() as AdminDashboardActivity).showConfirmationDialog(
			"Warning",
			"You are going to Delete " + content.name,
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
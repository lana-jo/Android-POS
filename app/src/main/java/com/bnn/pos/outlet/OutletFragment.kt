package com.bnn.pos.outlet

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
import com.bnn.pos.databinding.FragmentOutletBinding
import com.bnn.pos.model.OutletModel


class OutletFragment : Fragment() {

	var can: Int = 1 // user action, if 1 add, 2 edit

	private lateinit var binding: FragmentOutletBinding
	private lateinit var outletAdapter: OutletAdapter
	/*companion object {
		fun newInstance() = OutletFragment()
	}*/
//	private lat
	private var dialog: Dialog? = null
	private lateinit var data: OutletModel

	private lateinit var viewModel: OutletViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding = FragmentOutletBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(requireActivity())[OutletViewModel::class.java]
		data = OutletModel()

		binding.outletSearch.addTextChangedListener(object :TextWatcher{
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

			}

			override fun afterTextChanged(s: Editable?) {
				data.outletName = s.toString()
//				outletAdapter.setItem(listOf(data))
			}
		})


		outletAdapter = OutletAdapter(object : OutletAdapter.OutletAdapterListener{
			override fun onClick(model: OutletModel) {
				val dialogItem = arrayOf<CharSequence>(
					"Edit", "Delete"
				)
				val dialog: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
				dialog.setItems(dialogItem) { dialogInterface, i ->
					when (i) {
						0 -> {
							can = 2
							val intent = Intent(requireActivity(), OutletEditActivity::class.java)
							intent.putExtra("can", can)
							intent.putExtra("outlet_id", model.id)
							intent.putExtra("name", model.outletName )
							intent.putExtra("address", model.outletAddress )
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

		binding.outletList.adapter = outletAdapter
		binding.outletList.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
		binding.outletList.setHasFixedSize(true)

		binding.outletBtnAdd.setOnClickListener {
			can = 1
			val intent = Intent(requireActivity(), OutletEditActivity::class.java)
			intent.putExtra("can", can)
			startActivity(intent)
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getOutletList()

		Log.d("fragment", "onViewCreated: ${viewModel.outletListLiveData}")

		viewModel.outletListLiveData.observe(requireActivity()){
			if (it!=null) {
				outletAdapter.setItem(it)
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



}
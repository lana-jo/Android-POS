package com.bnn.pos.employee

import android.annotation.SuppressLint
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bnn.pos.databinding.LayoutItemEmployeeBinding
import com.bnn.pos.model.UserModel

class EmployeeAdapter(private val employeeListener: EmployeeAdapterListener) :
	RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

	var employeeList: MutableList<UserModel> = mutableListOf()
	var empModel = UserModel()

	interface EmployeeAdapterListener {
		fun click(model: UserModel)
	}

	class ViewHolder(item: LayoutItemEmployeeBinding) : RecyclerView.ViewHolder(item.root) {
		private val binding = item

		fun setBinding(model: UserModel, listener: EmployeeAdapterListener) {
//			model.role == 2
			/*if(model.role == 2) {*/
//			if (model.role !== 1) {
				binding.employeeName.text = model.name
				binding.employeePosition.text = model.position
				binding.employeeOutlet.text = model.outlet
				binding.employeeWhatsapp.setOnClickListener {
					"https://api.whatsapp.com/send?phone="+PhoneNumberUtils.formatNumber(
						model.phone, "ID"
					)
				}
				binding.employeeTelpon.setOnClickListener {
					PhoneNumberUtils.formatNumber(model.phone, "ID")
				}
				binding.employeeEmail.setOnClickListener {
					model.email
				}
				binding.listEmployee.setOnClickListener {
					listener.click(model)
				}
//			}
		/*binding.employeeName.text = model.name
				binding.employeePosition.text = model.position
				binding.employeeOutlet.text = model.outlet
				binding.employeeWhatsapp.setOnClickListener {
					"https://api.whatsapp.com/send?phone="+PhoneNumberUtils.formatNumber(
						model.phone, "ID"
					)
				}
				binding.employeeTelpon.setOnClickListener {
					PhoneNumberUtils.formatNumber(model.phone, "ID")
				}
				binding.employeeEmail.setOnClickListener {
					model.email
				}
				binding.listEmployee.setOnClickListener {
					listener.click(model)
				}*/			/*}
			else {
				binding.listEmployee.visibility = View.GONE
				binding.employeeName. visibility = View.GONE
				binding.employeePosition. visibility = View.GONE
				binding.employeeOutlet. visibility = View.GONE
				binding.employeeWhatsapp. visibility = View.GONE
				binding.employeeTelpon. visibility = View.GONE
				binding.employeeEmail. visibility = View.GONE
			}*/
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			LayoutItemEmployeeBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.setBinding(employeeList[position], employeeListener)
	}

	override fun getItemCount(): Int {
		return employeeList.size
	}

	override fun getItemViewType(position: Int): Int {
		return super.getItemViewType(position)
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setItem(list: List<UserModel>) {
			employeeList.clear()
			for (l in list) {
				if (!employeeList.contains(l)) {
					employeeList.add(l)

				}
			}
			notifyDataSetChanged()
	}

}
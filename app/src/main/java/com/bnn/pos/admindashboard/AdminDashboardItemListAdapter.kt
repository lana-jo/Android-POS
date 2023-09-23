package com.bnn.pos.admindashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bnn.pos.Tools
import com.bnn.pos.databinding.LayoutItemStatListBinding
import com.bnn.pos.model.AdminDasboardItemListModel

class AdminDashboardItemListAdapter :
	RecyclerView.Adapter<AdminDashboardItemListAdapter.ViewHolder>() {

	private val itemlist: MutableList<AdminDasboardItemListModel> = mutableListOf()

	class ViewHolder(item: LayoutItemStatListBinding) : RecyclerView.ViewHolder(item.root) {
		private val binding = item

		fun setBinding(model: AdminDasboardItemListModel) {
			binding.dashboardItemTitle.text = model.itemTitle
			binding.dashboardItemContent.text =
				Tools.formatNumber(model.itemContent!!.toDouble(), false)
		}

	}

	override fun onCreateViewHolder(
		parent: ViewGroup, viewType: Int
	): ViewHolder {
		return ViewHolder(
			LayoutItemStatListBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.setBinding(itemlist[position])
	}

	override fun getItemCount(): Int {
		return itemlist.size
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setList(list: List<AdminDasboardItemListModel>) {
		itemlist.clear()
		for (l in list) {
			if (!itemlist.contains(l)) {
				itemlist.add(l)
			}
		}
//		itemlist.addAll(list)
		notifyDataSetChanged()
	}
}
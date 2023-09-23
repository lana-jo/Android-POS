package com.bnn.pos.outlet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bnn.pos.databinding.LayoutItemOutletBinding
import com.bnn.pos.model.OutletModel

class OutletAdapter (private val outletListener: OutletAdapterListener) : RecyclerView.Adapter<OutletAdapter.ViewHolder>()  {

	private var outletList: MutableList<OutletModel> = mutableListOf()
	class ViewHolder(item: LayoutItemOutletBinding) : RecyclerView.ViewHolder(item.root) {
		private val binding = item

		fun setBinding(model: OutletModel, listener: OutletAdapterListener){
			binding.outletAlamat.text = model.outletAddress
			binding.outletName.text = model.outletName
			binding.outletEmployee.text = String.format("%s\nEmployees",model.outletUser.toString())

			binding.listOutlet.setOnClickListener {
				listener.onClick(model)
			}

		}

	}

	interface OutletAdapterListener {
		fun onClick(model: OutletModel)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutItemOutletBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}



	override fun getItemCount(): Int {
		return outletList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.setBinding(outletList[position], outletListener)
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setItem(list: List<OutletModel>){
		outletList.clear()
		for (l in list){
			if (!outletList.contains(l)){
				outletList.add(l)
			}
		}
		notifyDataSetChanged()
	}

}
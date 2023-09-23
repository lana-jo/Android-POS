package com.bnn.pos.admindashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bnn.pos.databinding.LayoutItemBarangBinding
import com.bnn.pos.model.ProductModel
import java.text.NumberFormat
import java.util.Locale


//class ProductAdapter(private val itemListener: DashboardItemListener) :
class ProductAdapter :
	RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

	private val itemList: MutableList<ProductModel> = mutableListOf()

	interface DashboardItemListener {
		fun onClick()
	}

	inner class ViewHolder(item: LayoutItemBarangBinding) : RecyclerView.ViewHolder(item.root) {
		private val binding = item

		fun setBinding(item: ProductModel) {
			binding.itemName.text = item.name
			binding.itemPrice.text = item.price?.let { formatRupiah(it.toDouble()) }
			binding.itemQuantity.text = "Quantity: " + item.quantity
			/*binding.itemContainer.setOnClickListener {
//				itemListener.onClick()
			}*/
		}
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			LayoutItemBarangBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun getItemCount(): Int {
		return itemList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.setBinding(itemList[position])
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setItem(list: List<ProductModel>){
		itemList.clear()
		for (l in list){
			if (!itemList.contains(l)){
				itemList.add(l)
			}
		}
		notifyDataSetChanged()
	}
	fun formatRupiah(number: Double): String? {
		val localeID = Locale("in", "ID")
		val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
		return formatRupiah.format(number)
	}
}
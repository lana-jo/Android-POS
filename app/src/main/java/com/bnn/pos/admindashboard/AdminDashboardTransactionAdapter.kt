package com.bnn.pos.admindashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bnn.pos.R
import com.bnn.pos.databinding.LayoutDashboardTransactionBinding
import com.bnn.pos.model.TransactionModel
import java.text.NumberFormat
import java.util.Locale


class AdminDashboardTransactionAdapter :
	RecyclerView.Adapter<AdminDashboardTransactionAdapter.ViewHolder>() {

	var transactionList: MutableList<TransactionModel> = mutableListOf()

	class ViewHolder(item: LayoutDashboardTransactionBinding) : RecyclerView.ViewHolder(item.root) {
		private val binding = item
		fun setBinding(data: TransactionModel) {
			if (data.status == 1) {
				binding.transactionContainer.setBackgroundResource(R.drawable.shape_background_rounded_success)
				binding.transactionIcon.setImageResource(R.drawable.ic_checklist_circle)
				binding.transactionStatus.text = "Transaction Successfully"
				binding.transactionSeller.text = String.format("Seller: %s", data.seller)
				binding.transactionOutlet.text = data.outlet
				binding.transactionAmount.text = data.transaksi?.let { formatRupiah(it.toDouble()) }
				binding.transactionMethod.text = data.metode
				binding.transactionDate.text = data.tanggal
			} else {
				binding.transactionContainer.setBackgroundResource(R.drawable.shape_background_rounded_danger)
				binding.transactionIcon.setImageResource(R.drawable.ic_failled_circle)
				binding.transactionStatus.text = "Transaction Failure"
				binding.transactionAmount.text = data.transaksi?.let { formatRupiah(it.toDouble()) }
				binding.transactionSeller.visibility = View.INVISIBLE
				binding.transactionMethod.visibility = View.INVISIBLE
				binding.transactionDate.text = data.tanggal
			}
		}

		fun formatRupiah(number: Double): String? {
			val localeID = Locale("in", "ID")
			val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
			return formatRupiah.format(number)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			LayoutDashboardTransactionBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun getItemCount(): Int {
		return transactionList.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.setBinding(transactionList[position])
	}

	@SuppressLint("NotifyDataSetChanged")
	fun setItem(list: List<TransactionModel>) {
		transactionList.clear()
		for (l in list) {
			if (!transactionList.contains(l)) {
				transactionList.add(l)
			}
		}
		notifyDataSetChanged()
	}

}
package com.bnn.pos.admindashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bnn.pos.databinding.FragmentAdminDashboardBinding
import com.bnn.pos.model.TransactionModel
import com.bnn.pos.transaction.TransactionActivity
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.Locale


class AdminDashboardFragment : Fragment() {

	/*   companion object {
		   fun newInstance() = AdminDashboardFragment()
	   }*/

	private lateinit var itemListAdapter: AdminDashboardItemListAdapter

	private lateinit var itemAdapter: ProductAdapter
	private lateinit var transactionAdapter: AdminDashboardTransactionAdapter

	private lateinit var binding: FragmentAdminDashboardBinding
	private lateinit var dashboardViewModel: AdminDashboardViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)

		dashboardViewModel =
			ViewModelProvider(requireActivity())[AdminDashboardViewModel::class.java]


		binding.dashboardMenuTransaction.setOnClickListener {
			startActivity(Intent(requireActivity(), TransactionActivity::class.java))
		}

		/*binding.dashboardMenuItem.setOnClickListener {
			startActivity(Intent(requireActivity(), ProductActivity::class.java))
		}*/
		itemListAdapter = AdminDashboardItemListAdapter()
		itemAdapter = ProductAdapter()
		transactionAdapter = AdminDashboardTransactionAdapter()

		binding.dashboardDetailItem.adapter = itemListAdapter
		binding.dashboardDetailItem.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
		binding.dashboardDetailItem.setHasFixedSize(true)

		/*	binding.dashboardItemList.adapter = itemAdapter
			binding.dashboardItemList.layoutManager =
				GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
			binding.dashboardItemList.setHasFixedSize(true)*/

		binding.dashboardLastTransaction.adapter = transactionAdapter
		binding.dashboardLastTransaction.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		binding.dashboardLastTransaction.setHasFixedSize(true)

		showBarChart()

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		dashboardViewModel.itemDashboardListLiveData.observe(requireActivity()) {
			if (it != null) {
				itemListAdapter.setList(it)
			}
		}
		dashboardViewModel.cleanItem()
//		dashboardViewModel.getReport()
		dashboardViewModel.getTransaction()


		dashboardViewModel.transactionDashboardLiveData.observe(requireActivity()) {
			if (it != null) {
//				BarDataSet(it, "Laporan")
				Log.d("adapter", "onViewCreated: $it")
				transactionAdapter.setItem(it)

			}
		}        /*dashboardViewModel.reportDashboardLiveData.observe(requireActivity()){
			if (it!=null){
				BarDataSet(it, "Laporan")
			}
		}*/
//		dashboardViewModel.load()
//		Log.d("isi", "load: "+dashboardViewModel.load())
		dashboardViewModel.getData()

		dashboardViewModel.itemDashboardLiveData.observe(requireActivity()) {
			if (it != null) {
				itemAdapter.setItem(it)
			}
		}


	}

	private fun showBarChart() {
		val valueList = ArrayList<Double>()
		val entries = ArrayList<BarEntry>()
		var title = "Sales"
//		var title = ArrayList<String>()

		binding.barChart.axisRight.setDrawLabels(false)
//		var y = binding.barChart.axisLeft

		val json = dashboardViewModel.readFromAssets("report.json")
		val list = Gson().fromJson(json, Array<TransactionModel>::class.java).toMutableList()
		Log.d("getdata", "getdata: $list")

		/*val xAxis: XAxis = binding.barChart.getXAxis()
		xAxis.position = XAxisPosition.BOTTOM
		xAxis.textSize = 10f
		xAxis.textColor = R.color.color_primary_dark
		xAxis.setDrawAxisLine(true)
		xAxis.setDrawGridLines(true)
		// set a custom value formatter
		xAxis.valueFormatter = Xformater()*/


		//input data
		for (l in list) {
			if (l.status == 1) {
				/*xAxis.setDrawLabels(true)*/
//				title = l.tanggal.toString()
//				title.add(l.tanggal.toString())
				valueList.add(l.transaksi!!.toDouble())
				Log.d("chart", "showBarChart: ${l.transaksi} ")
			}
		}


		//fit the data into a bar
		for (i in valueList.indices) {
			val barEntry = BarEntry(i.toFloat(), valueList[i].toFloat())
			entries.add(barEntry)
		}

		binding.barChart.animateY(5000)

		var barDataSet = BarDataSet(entries, title)
		var data = BarData(barDataSet)
		initBarDataSet(barDataSet)
		binding.barChart.setData(data)
		binding.barChart.invalidate()
	}

	private fun initBarDataSet(barDataSet: BarDataSet) {    //Changing the color of the bar
		barDataSet.color = Color.parseColor("#00CC66") //Setting the size of the form in the legend
		barDataSet.formSize = 15f //showing the value of the bar, default true if not set
		barDataSet.setDrawValues(false) //setting the text size of the value of the bar
		barDataSet.valueTextSize = 12f
	}

	class Xformater : ValueFormatter() {
		private val days = arrayOf("Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su")

		override fun getAxisLabel(value: Float, axis: AxisBase?): String {
			return days.getOrNull(value.toInt()) ?: value.toString()
		}
	}

	fun formatRupiah(number: Double): String? {
		val localeID = Locale("in", "ID")
		val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
		return formatRupiah.format(number)
	}

	/*fun <BarEntry> getReport(): MutableList<AdminDashboardViewModel.reportModel> {

		return list
	}*/
	fun getReport(fileName: String): String {
		val bufferReader = requireActivity().assets.open(fileName).bufferedReader()
		return bufferReader.use {
			it.readText()
		}
	}
}
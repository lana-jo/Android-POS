package com.bnn.pos.admindashboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bnn.pos.model.AdminDasboardItemListModel
import com.bnn.pos.model.ProductModel
import com.bnn.pos.model.TransactionModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class AdminDashboardViewModel(application: Application) : AndroidViewModel(application) {
	val itemDashboardListLiveData: MutableLiveData<MutableList<AdminDasboardItemListModel>> =
		MutableLiveData()
	val itemDashboardLiveData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()
//	val reportDashboardLiveData: MutableLiveData<List<reportModel>> = MutableLiveData()
	val transactionDashboardLiveData: MutableLiveData<MutableList<TransactionModel>> = MutableLiveData()

	/*data class reportModel(
		@SerializedName("status") var status: Int? = 0,

		@SerializedName("seller") var seller: String? = null,

		@SerializedName("outlet") var outlet: String? = null,

		@SerializedName("tanggal") var tanggal: String? = null,

		@SerializedName("transaksi") var transaksi: String? = null,
		*//*@SerializedName("status") var status: Int ? = 0,
		@SerializedName("tanggal")
		var tanggal: String? = null,
		@SerializedName("transaksi")
		var transaksi: String? = null*//*
	)*/

	data class contentItem(
		@SerializedName("total_penjualan") var totalPenjualan: Int? = 0,
		@SerializedName("total_transaksi") var totalTransaksi: Int? = 0,
		@SerializedName("total_laba_rugi") var totalLabaRugi: Int? = 0,
		@SerializedName("total_laba_bersih") var totalLabaBersih: Int? = 0
	)

/*
	data class itemModel(
		@SerializedName("item_name") var name: String? = null,

		@SerializedName("item_quantity") var quantity: String? = null,

		@SerializedName("item_price") var price: String? = null,

		@SerializedName("outlet_name") var outletName: String? = null,
	)
*/

//	val itemLiveData: MutableLiveData<List<itemModel>> = MutableLiveData()
//	val  product: LiveData<contentItem> = contentItem

	fun cleanItem() {
		setitemdashboard(/*listOf(*/contentItem(0, 0, 0, 0/*)*/))
	}

	fun setitemdashboard(model: /*List<*/contentItem/*>*/) {
		val json = readFromAssets("dashboard.json")
		val data: MutableList<contentItem> =
			Gson().fromJson(json, Array<contentItem>::class.java).toMutableList()


		var list: MutableList<AdminDasboardItemListModel> = mutableListOf()
		var itemModel = AdminDasboardItemListModel()

//		for (m in model) {
//			if (!data.contains(m))
//				itemModel = AdminDasboardItemListModel()
			itemModel.itemTitle = "Sales"
			itemModel.itemContent = model.totalPenjualan
//			itemModel.itemContent = m.totalPenjualan
//			.itemContent = datas.totalPenjualan
			list.add(itemModel)

			itemModel = AdminDasboardItemListModel()
			itemModel.itemTitle = "Transactions"
			itemModel.itemContent = model.totalTransaksi
//			itemModel.itemContent = m.totalTransaksi
//			itemModel.itemContent = datas.totalTransaksi
			list.add(itemModel)

			itemModel = AdminDasboardItemListModel()
			itemModel.itemTitle = "Income Statement"
			itemModel.itemContent = model.totalLabaRugi
//			itemModel.itemContent = m.totalLabaRugi
//			itemModel.itemContent = datas.totalLabaRugi
			list.add(itemModel)

			itemModel = AdminDasboardItemListModel()
			itemModel.itemTitle = "Net income"
			itemModel.itemContent = model.totalLabaBersih
//			itemModel.itemContent = m.totalLabaBersih
//			itemModel.itemContent = datas.totalLabaBersih
			list.add(itemModel)


			itemDashboardListLiveData.postValue(list)
//		}


	}

	fun load() {
		val json = readFromAssets("dashboard.json")
		val data: MutableList<contentItem> =
			Gson().fromJson(json, Array<contentItem>::class.java).toMutableList()
		Log.d("data", "data: $data")
	}

	fun getData() {
		val json = readFromAssets("barang.json")
		val list = Gson().fromJson(json, Array<ProductModel>::class.java).toMutableList()
		Log.d("getdata", "getdata: $list")
		itemDashboardLiveData.postValue(list)
	}

	fun getReport() {
		/*val json = readFromAssets("report")
		val list = Gson().fromJson(json, Array<reportModel>::class.java).toMutableList()
		Log.d("getdata", "getdata: $list")

		reportDashboardLiveData.postValue(list)*/
	}

	fun getTransaction() {
		val json = readFromAssets("report.json")
		val translist = Gson().fromJson(json, Array<TransactionModel>::class.java).toMutableList()
		transactionDashboardLiveData.postValue(translist)
	}

	fun readFromAssets(fileName: String): String {
		val bufferReader =
			getApplication<Application>().applicationContext.assets.open(fileName).bufferedReader()
		return bufferReader.use {
			it.readText()
		}
	}
}
package com.bnn.pos.outlet

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bnn.pos.ConnectionApi
import com.bnn.pos.ConnectionListener
import com.bnn.pos.model.OutletModel
import com.bnn.pos.model.RequestModel
import com.bnn.pos.model.ResultModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class OutletViewModel(application: Application) : AndroidViewModel(application) {
	var outletListLiveData: MutableLiveData<MutableList<OutletModel>?> = MutableLiveData()
	val resultModelLiveData: MutableLiveData<ResultModel?> = MutableLiveData()
	var dataModel = OutletModel()
	var dummyArrayObject = mutableListOf(
		{ " \"id\": 1,\n"+"    \"name\": \"outlet 1\",\n"+"    \"address\": \"Jl. Menoreh Raya No.76a, Bendan Duwur, Kec. Gajahmungkur, Kota Semarang, Jawa Tengah 50235\",\n"+"    \"outlet_user\": 21" },
		"{\"id\":2,\"name\":\"outlet 2\",\"address\":\"Jl. Tlogosari Raya II No.21, Tlogosari Kulon, Kec. Pedurungan, Kota Semarang, Jawa Tengah 50196\"}",
		"{\"id\": 3,\n"+"\"name\": \"outlet 3\",\n"+"\"address\": \"Jl. Tlogosari Raya II No.21, Tlogosari Kulon, Kec. Pedurungan, Kota Semarang, Jawa Tengah 50196\"}")


//	var cek = dummyArrayObject.equals(dataModel)
	var dummy =
		mutableListOf("\"id\": 1,\n"+"    \"name\": \"outlet 1\",\n"+"    \"address\": \"Jl. Menoreh Raya No.76a, Bendan Duwur, Kec. Gajahmungkur, Kota Semarang, Jawa Tengah 50235\",\n"+"    \"outlet_user\": 21,\n"+"\"id\": 2,\n"+"    \"name\": \"outlet 2\",\n"+"    \"address\": \"Jl. Tlogosari Raya II No.21, Tlogosari Kulon, Kec. Pedurungan, Kota Semarang, Jawa Tengah 50196\",\n"+"    \"outlet_user\": 22,\n"+" \"id\": 3,\n"+"    \"name\": \"outlet 3\",\n"+"    \"address\": \"Jl. KaranganyarNo.578, Brumbumgan, Gabahan, Semarang Tengah, Semarang City, Central Java 50135\",\n"+"    \"outlet_user\": 23,\n"+"\"id\": 4,\n"+"    \"name\": \"outlet 4\",\n"+"    \"address\": \"Jl. Jati Raya, Srondol Wetan, Kec. Banyumanik, Kota Semarang, Jawa Tengah 50263\",\n"+"    \"outlet_user\": 24")
	var data = mutableListOf<OutletModel>()
	var dummyData = mutableListOf<OutletModel>()
//	var data
	var outletType = object : TypeToken<MutableList<OutletModel>>() {}.type
//	var datalist = Gson().fromJson(dummyData, outletType)
//	var kiw = dataModel.id?.let { dummy.get(it) }
//	var kiw = dummy[]

	/*var create = data.add(dataModel)
	var read= data[dataModel.id!!]
	var updated = data[dataModel.id!!]
	var delete = data.removeAt(dataModel.id!!)
*/
	var get = outletListLiveData.postValue(data)

	/*var json = readFromAssets("outlet.json")
	var list: MutableList<OutletModel> = Gson().fromJson(json, Array<OutletModel>::class.java).toMutableList()*/

	private val connectionApi = ConnectionApi(object : ConnectionListener {
		override fun onResult(model: ResultModel) {
			if (model.status == 0) {
				onError(model)
			} else {
				when (model.action) {
					"load-data" -> {
						if (model.data != null) {
							val him = object : TypeToken<MutableList<OutletModel>>() {}.type
							val list: MutableList<OutletModel> = Gson().fromJson(model.data, him)
//							outletListLiveData.postValue(list)
							outletListLiveData.postValue(data)
						}
					}
				}
			}
		}

		override fun onError(model: ResultModel) {
			resultModelLiveData.postValue(model)
		}

	})


	fun getOutletList() {
//		if (read !== null){
//			read
//		}
		var requestModel = RequestModel()
		requestModel.action = "load-data"
//		requestModel.data = Gson().fromJson(OutletModel())
//		requestModel.data = dummyData.toString()
//		data
//		Gson().fromJson(dummy, OutletModel::class.java)
//		Gson().fromJson(dummy, data)

		get
//		Log.d("dummy", "getOutletList: $dummy ")

//		dummy.add(data.toString())
//		Log.d("dummy", "getOutletList: $kiw ")
		Log.d("dummy", "data: $data ")
//		Log.d("dummy", "dummy:  " +data[dataModel.id!!])
		/*var json = readFromAssets("outlet.json")
			var list: MutableList<OutletModel> = Gson().fromJson(json, Array<OutletModel>::class.java).toMutableList()
			Log.d("outletList", "getOutletList: $list")
			outletListLiveData.postValue(list)*/
	}

	fun inputFile(model: OutletModel) {

//		BufferedWriter(getFilesD FileWriter())
//		Gson().toJson(OutletModel::class.java, FileWriter("outlet.json"))
	}


	fun readFromAssets(fileName: String): String {
		val bufferReader =
			getApplication<Application>().applicationContext.assets.open(fileName).bufferedReader()
		return bufferReader.use {
			it.readText()
		}
	}
}
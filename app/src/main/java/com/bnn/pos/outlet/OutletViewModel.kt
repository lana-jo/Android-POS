package com.bnn.pos.outlet

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bnn.pos.model.OutletModel
import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.FileWriter


class OutletViewModel(application: Application) : AndroidViewModel(application)  {
	var outletListLiveData: MutableLiveData<MutableList<OutletModel>> = MutableLiveData()


	/*var json = readFromAssets("outlet.json")
	var list: MutableList<OutletModel> = Gson().fromJson(json, Array<OutletModel>::class.java).toMutableList()*/



	fun getOutletList(){
		var json = readFromAssets("outlet.json")
		var list: MutableList<OutletModel> = Gson().fromJson(json, Array<OutletModel>::class.java).toMutableList()
		Log.d("outletList", "getOutletList: $list")
		outletListLiveData.postValue(list)
	}
	fun inputFile(){
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
package com.bnn.pos.product

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bnn.pos.model.ProductModel
import com.google.gson.Gson

class ProductViewModel (application: Application) : AndroidViewModel(application){


	val productListLiveData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()
	fun getData() {
		val json = readFromAssets("barang.json")
		val list = Gson().fromJson(json, Array<ProductModel>::class.java).toMutableList()
		Log.d("getdata", "getdata: $list")
		productListLiveData.postValue(list)
	}

	fun readFromAssets(fileName: String): String {
		val bufferReader =
			getApplication<Application>().applicationContext.assets.open(fileName).bufferedReader()
		return bufferReader.use {
			it.readText()
		}
	}
}
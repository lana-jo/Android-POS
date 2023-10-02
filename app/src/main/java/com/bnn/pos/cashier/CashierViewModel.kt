package com.bnn.pos.cashier

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bnn.pos.model.ProductModel
import com.bnn.pos.model.UserModel
import com.google.gson.Gson

class CashierViewModel (application: Application) : AndroidViewModel(application) {

	var cashierListLiveData: MutableLiveData<MutableList<ProductModel>> = MutableLiveData()


	fun getData(){
		val json = readFromAssets("barang.json")
		val list = Gson().fromJson(json, Array<ProductModel>::class.java).toMutableList()
		cashierListLiveData.postValue(list)
	}

	fun readFromAssets(fileName: String): String {
		val bufferReader =
			getApplication<Application>().applicationContext.assets.open(fileName).bufferedReader()
		return bufferReader.use {
			it.readText()
		}
	}
}
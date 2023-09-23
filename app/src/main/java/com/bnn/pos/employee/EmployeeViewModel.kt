package com.bnn.pos.employee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bnn.pos.model.TransactionModel
import com.bnn.pos.model.UserModel
import com.google.gson.Gson

class EmployeeViewModel (application: Application): AndroidViewModel(application) {
	var employeeListLiveData: MutableLiveData<MutableList<UserModel>> = MutableLiveData()


	fun getData(){
		val json = readFromAssets("user.json")
		val list = Gson().fromJson(json, Array<UserModel>::class.java).toMutableList()
		employeeListLiveData.postValue(list)
	}

	fun readFromAssets(fileName: String): String {
		val bufferReader =
			getApplication<Application>().applicationContext.assets.open(fileName).bufferedReader()
		return bufferReader.use {
			it.readText()
		}
	}
}
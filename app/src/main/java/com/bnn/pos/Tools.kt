package com.bnn.pos

import android.util.Log
import com.bnn.pos.model.RequestModel
import com.bnn.pos.model.ResultModel
import java.text.DecimalFormat

object Tools {
	const val api = "http://127.0.0.1:8000/api/"
	var authToken: String? = null
	const val TAG = "POS-ANDROID"
	fun showLog(location: String, message: String) {
		Log.d(TAG, String.format("%s%s", String.format("%s,", location), message))
	}


	fun parseResult(resultModel: ResultModel?, requestDataModel: RequestModel): ResultModel {
		var result = resultModel
		if (result == null) {
			result = ResultModel(0, requestDataModel.message)
			if (requestDataModel.message == null) {
				requestDataModel.message = "No data from server"
				result = ResultModel(0, requestDataModel.message, requestDataModel.action)
			}

		}
		result.action = requestDataModel.action
		return result
	}
	fun formatNumber(d: Double = 0.0, wd: Boolean? = false): String {
		var format = DecimalFormat("###,###")
		if (wd == true){
			format = DecimalFormat("###,###.00")
		}

		val result = format.format(d)
		return result.replace(".", "#").replace(",", ".").replace("#",",")
	}
}
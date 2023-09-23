package com.bnn.pos

import com.google.gson.Gson
import okio.use
import java.text.DecimalFormat

object Tools {
	const val api = "http://127.0.0.1:8000/api/"

	fun formatNumber(d: Double = 0.0, wd: Boolean? = false): String {
		var format = DecimalFormat("###,###")
		if (wd == true){
			format = DecimalFormat("###,###.00")
		}

		val result = format.format(d)
		return result.replace(".", "#").replace(",", ".").replace("#",",")
	}
}
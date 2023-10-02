package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestModel: Serializable {

	var action: String? = null

	var data : String? = null

	var message : String? = null
	override fun toString(): String {
		return "RequestModel(action=$action, data=$data, message=$message)"
	}

}
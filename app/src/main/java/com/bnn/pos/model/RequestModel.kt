package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestModel: Serializable {

//	@SerializedName('action')
	var action: String? = null

//	@SerializedName('data')
	var data : String? = null

	var message : String? = null



}
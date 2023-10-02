package com.bnn.pos.model

import java.io.Serializable

class ResultModel : Serializable {

	var action: String? = null
	var status: Int? = null
	var message: String? = null

	var data: String? = null

	constructor(status: Int?, message: String?, action: String?) {
		this.status = status
		this.message = message
		this.action = action
	}

	constructor(status: Int?, message: String?) {
		this.status = status
		this.message = message
	}

	override fun toString(): String {
		return "ResultModel(action=$action, status=$status, message=$message, data=$data)"
	}


}
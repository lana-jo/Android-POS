package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel: Serializable {

	@SerializedName("phone")
	var phone: String? = null
	@SerializedName("name")
	var name: String? = null
	@SerializedName("pin")
	var pin: String? = null

	@SerializedName("role")
	var role: Int? = null

	override fun toString() : String {
		return "UserModel(phone=$phone, name=$name, role=$role)"
	}


}
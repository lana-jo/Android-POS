package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel : Serializable {


	@SerializedName("address")
	var address : String? = null

	@SerializedName("email")
	var email: String? = null

	@SerializedName("id")
	var id: Int? = null
//	var id: Int? = 0

	@SerializedName("outlet")
	var outlet: String? = null

	@SerializedName("position")
	var position: String? = null

	@SerializedName("phone")
	var phone: String? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("pin")
	var pin: String? = null

	@SerializedName("role")
	var role: Int? = null

	override fun toString(): String {
		return "UserModel(address=$address, email=$email, id=$id, outlet=$outlet, position=$position, phone=$phone, name=$name, pin=$pin, role=$role)"
	}


}
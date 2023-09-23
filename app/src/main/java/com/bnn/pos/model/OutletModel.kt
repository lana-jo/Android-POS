package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OutletModel: Serializable {
	@SerializedName("id")
	var id : Int? = 0
	@SerializedName("name")
	var outletName : String? = null
	@SerializedName("address")
	var outletAddress : String? = null

	@SerializedName("outlet_user")
	var outletUser : Int? = 0
	override fun toString(): String {
		return "OutletModel(id=$id, outletName=$outletName, outletAddress=$outletAddress, outletUser=$outletUser)"
	}


}
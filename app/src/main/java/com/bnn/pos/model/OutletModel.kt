package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OutletModel: Serializable {
	@SerializedName("id")
	var id: Int? = null

	@SerializedName("name")
	var outletName: String? = null

	@SerializedName("address")
	var outletAddress: String? = null

	@SerializedName("outlet_user")
	var outletUser: Int? = 0

	/*constructor(outletName: String?, outletAddress: String?, outletUser: Int?) {
		this.outletName = outletName
		this.outletAddress = outletAddress
		this.outletUser = outletUser
	}*/



	override fun toString(): String {
		return "OutletModel(id=$id, outletName=$outletName, outletAddress=$outletAddress, outletUser=$outletUser)"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as OutletModel

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id ?: 0
	}


}
package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProductModel: Serializable {
	@SerializedName("item_name")
	var name: String? = null

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("item_quantity")
	var quantity: String? = null

	@SerializedName("item_price")
	var price: String? = null

	@SerializedName("outlet_name")
	var outletName: String? = null
	override fun toString(): String {
		return "ProductModel(name=$name, quantity=$quantity, price=$price, outletName=$outletName)"
	}

}
package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TransactionModel: Serializable {
	@SerializedName("status")
	var status: Int? = 0

	@SerializedName("seller")
	var seller: String? = null

	@SerializedName("outlet")
	var outlet : String? = null

	@SerializedName("tanggal")
	var tanggal: String? = null

	@SerializedName("metode")
	var metode: String? = null

	@SerializedName("transaksi")
	var transaksi: String? = null
	override fun toString(): String {
		return "TransactionModel(status=$status, seller=$seller, outlet=$outlet, tanggal=$tanggal, transaksi=$transaksi)"
	}


}
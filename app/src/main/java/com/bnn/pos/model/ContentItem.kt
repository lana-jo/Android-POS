package com.bnn.pos.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContentItem : Serializable {
	@SerializedName("total_penjualan")
	var totalPenjualan: Int? = 0
	@SerializedName("total_transaksi")
	var totalTransaksi: Int? = 0
	@SerializedName("total_laba_rugi")
	var totalLabaRugi: Int? = 0
	@SerializedName("total_laba_bersih")
	var totalLabaBersih: Int? = 0
}
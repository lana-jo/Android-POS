package com.bnn.pos.cashier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnn.pos.R
import com.bnn.pos.databinding.ActivityCashierBinding

class CashierActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCashierBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCashierBinding.inflate(layoutInflater)
		setContentView(binding.root)


	}

}
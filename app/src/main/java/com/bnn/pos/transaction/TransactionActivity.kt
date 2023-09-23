package com.bnn.pos.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnn.pos.R
import com.bnn.pos.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity() {

	private lateinit var binding : ActivityTransactionBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityTransactionBinding.inflate(layoutInflater)
		setContentView(binding.root)

	}
}
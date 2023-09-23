package com.bnn.pos.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnn.pos.databinding.ActivityItemBinding

class ProductActivity : AppCompatActivity() {

	private lateinit var binding: ActivityItemBinding
//	private var _binding: get() = binding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityItemBinding.inflate(layoutInflater)
		setContentView(binding.root)


	}
}
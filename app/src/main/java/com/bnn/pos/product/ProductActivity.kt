package com.bnn.pos.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnn.pos.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

	private lateinit var binding: ActivityProductBinding
//	private var _binding: get() = binding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityProductBinding.inflate(layoutInflater)
		setContentView(binding.root)


	}
}
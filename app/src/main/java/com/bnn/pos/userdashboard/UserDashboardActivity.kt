package com.bnn.pos.userdashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnn.pos.R
import com.bnn.pos.databinding.ActivityUserDashboardBinding

class UserDashboardActivity : AppCompatActivity() {
	private lateinit var binding : ActivityUserDashboardBinding
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityUserDashboardBinding.inflate(layoutInflater)
		setContentView(binding.root)


	binding

	}
}
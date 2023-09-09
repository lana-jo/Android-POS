package com.bnn.pos.admindashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bnn.pos.R
import com.bnn.pos.databinding.ActivityAdminDashboardBinding

class AdminDashboardActivity : AppCompatActivity() {
	private lateinit var binding : ActivityAdminDashboardBinding

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
		setContentView(binding.root)


	}
}
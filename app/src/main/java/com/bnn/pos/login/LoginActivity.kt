package com.bnn.pos.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bnn.pos.admindashboard.AdminDashboardActivity
import com.bnn.pos.databinding.ActivityLoginBinding
import com.bnn.pos.model.UserModel
import com.bnn.pos.userdashboard.UserDashboardActivity
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

	private var userModel = UserModel()

	var phone : String? = null
	var pin : String? = null

	private lateinit var binding : ActivityLoginBinding
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val data = assets.open("user.json")

		val json = data.bufferedReader().use { it.readText() }

		var validation = Gson().toJson(json)

		binding.loginPhone.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s : CharSequence? ,
				start : Int ,
				count : Int ,
				after : Int
			) {
			}

			override fun onTextChanged(
				s : CharSequence? ,
				start : Int ,
				before : Int ,
				count : Int
			) {

			}

			override fun afterTextChanged(s : Editable?) {
				phone = s.toString()
				userModel.phone = phone
			}

		})
		binding.loginPhone.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s : CharSequence? ,
				start : Int ,
				count : Int ,
				after : Int
			) {

			}

			override fun onTextChanged(
				s : CharSequence? ,
				start : Int ,
				before : Int ,
				count : Int
			) {

			}

			override fun afterTextChanged(s : Editable?) {
				pin = s.toString()
				userModel.pin = pin
			}

		})

		binding.loginBtn.setOnClickListener {

			Log.d("isi" , "isi validtation " + validation)
			validation
			if (userModel.role === 1) startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
			else startActivity(Intent(this@LoginActivity, UserDashboardActivity::class.java))
		}

	}
}
package com.bnn.pos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.antohuang.dialogsheet.DialogSheet
import com.bnn.pos.admindashboard.AdminDashboardActivity
import com.bnn.pos.databinding.ActivityLoginBinding
import com.bnn.pos.databinding.LayoutDialogInformationBinding
import com.bnn.pos.model.UserModel
import com.bnn.pos.userdashboard.UserDashboardActivity
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

	private var userModel = UserModel()
	private lateinit var dialogSheet: DialogSheet

	var phone: String? = null
	var pin: String? = null

	private lateinit var binding: ActivityLoginBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val data = assets.open("user.json")

		val json = data.bufferedReader().use { it.readText() }

		var validation = Gson().toJson(json)

		binding.loginPhone.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s: CharSequence?, start: Int, count: Int, after: Int
			) {

			}

			override fun onTextChanged(
				s: CharSequence?, start: Int, before: Int, count: Int
			) {

			}

			override fun afterTextChanged(s: Editable?) {
				phone = s.toString()
				userModel.phone = phone
			}

		})
		binding.loginPhone.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s: CharSequence?, start: Int, count: Int, after: Int
			) {

			}

			override fun onTextChanged(
				s: CharSequence?, start: Int, before: Int, count: Int
			) {

			}

			override fun afterTextChanged(s: Editable?) {
				pin = s.toString()
				userModel.pin = pin
			}

		})

		binding.loginBtn.setOnClickListener {

			Log.d("isi", "isi validation "+validation)
			if (phone == null || phone.toString().length < 10 || phone.toString()
					.startsWith("01")) {
				showInformationDialog(
					getString(R.string.title_warning),
					"Nomor HP/Telepon minimal 10 angka dan dimulai dengan 08"
				)
				binding.loginPhone.requestFocus();
				return@setOnClickListener
			}
			if (phone !== validation){
				showInformationDialog(
					getString(R.string.title_warning),
					"Nomor HP/Telepon yang anda masukkan salah"
				)
				binding.loginPhone.requestFocus();
				return@setOnClickListener
			}
			if (pin == null || pin.toString().length != 6) {
				showInformationDialog(getString(R.string.title_warning), "Password harus 6 angka")
				binding.loginPin.requestFocus();
				return@setOnClickListener
			}

			validation
			if (userModel.role === 1) startActivity(
				Intent(
					this@LoginActivity, AdminDashboardActivity::class.java
				)
			)
			else startActivity(Intent(this@LoginActivity, UserDashboardActivity::class.java))
		}
		}

	}

	private fun showInformationDialog(title: String, content: String, close: Boolean = false) {
		val infoBinding = LayoutDialogInformationBinding.inflate(layoutInflater)
		dialogSheet = DialogSheet(this).setCancelable(false).setView(infoBinding.root)

		infoBinding.infoTitle.text = title
		infoBinding.infoContent.text = content
		infoBinding.infoButton.setOnClickListener {
			dialogSheet.dismiss()

			if (close) {
				Tools.isOnLogin = false
				finish()
				startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java))
			}
		}

		dialogSheet.show()
	}


}
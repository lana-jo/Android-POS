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
import okio.use
import java.io.FileWriter
import java.io.IOException
import java.io.InputStream

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


		val inputStream= assets.open("user.json")
		val size: Int = inputStream.available()
		val buffer = ByteArray(size)
		inputStream.read(buffer)
		inputStream.close()
		val json = String(buffer)

		// Use Gson to parse the JSON into your model class
		val gson = Gson()
		val user = gson.fromJson(json, Array<UserModel>::class.java).toMutableList()



		for (u in user){
			/*userModel.id = u.id
			userModel.role = u.role
			userModel.phone= u.phone
			userModel.pin = u.pin*/
			userModel = u
			/*u.id = userModel.id
			u.role = userModel.role
			u.phone = userModel.phone
			u.pin = userModel.pin*/
		}

		/*var data= assets.open("user.json")
		val json = data.bufferedReader().use { it.readText() }

//		FileWriter.
		var validation = Gson().toJson(json)*/
//		var get = Gson().fromJson(json)
//		var get = Gson().fromJson(json, datauser(userModel.id, userModel.name, userModel.pin, userModel.phone) )


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
				/*phone = s.toString()
				userModel.phone = phone*/
				userModel.phone = s.toString()
				Log.d("pin", "afterTextChanged: ${userModel.phone}")

			}

		})
		binding.loginPin.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(
				s: CharSequence?, start: Int, count: Int, after: Int
			) {

			}

			override fun onTextChanged(
				s: CharSequence?, start: Int, before: Int, count: Int
			) {
			/*	binding.loginBtn.isEnabled = phone != null && phone.toString().length > 9 && pin != null && pin.toString().length > 3 && s.toString()
					.isNotEmpty()*/
			}

			override fun afterTextChanged(s: Editable?) {
				/*pin = s.toString()
				userModel.pin = pin*/
				userModel.pin = s.toString()
				Log.d("pin", "afterTextChanged: ${userModel.pin}")
			}

		})

		binding.loginBtn.setOnClickListener {

			user

//			login()
			Log.d("isi", "isi validation "+user)
//			dummy()
			/*Log.d("isi", "isi validation "+validation)
			validation*/
			if (userModel.phone == null || userModel.phone.toString().length < 10 || userModel.phone.toString()
					.startsWith("01")) {
				showInformationDialog(
					getString(R.string.title_warning),
					"Nomor HP/Telepon minimal 10 angka dan dimulai dengan 08"
				)
				binding.loginPhone.requestFocus()
				return@setOnClickListener
			}

			if (pin == null || pin.toString().length != 6) {
				showInformationDialog(getString(R.string.title_warning), "PIN harus 6 angka")
				binding.loginPin.requestFocus()
				return@setOnClickListener
			}
			/*if (userModel.pin !== validation['pin']) {
			showInformationDialog(getString(R.string.title_warning), "PIN Yang anda masukkan salah")
			binding.loginPin.requestFocus()
			return@setOnClickListener
		}

			if (userModel.phone != validation['phone']) {
				showInformationDialog(
					getString(R.string.title_warning),
					"Nomor yang anda masukkan salah"
				)
				binding.loginPhone.requestFocus()
				return@setOnClickListener
			}*/


//			if (validation["role"] != 1) {
			if (userModel.role === 1) {
				startActivity(
					Intent(
						this@LoginActivity, UserDashboardActivity::class.java
					)
				)
			} else {
				startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
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

			/*if (close || userModel.role === 1) {
//				dummy()
				finish()
				startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
//				startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
			} else {
				startActivity(Intent(this@LoginActivity, UserDashboardActivity::class.java))
			}*/
		}

		dialogSheet.show()
	}

	fun login(){
		try {
			val inputStream: InputStream = assets.open("user.json")
			val size: Int = inputStream.available()
			val buffer = ByteArray(size)
			inputStream.read(buffer)
			inputStream.close()
			val json = String(buffer)

			// Use Gson to parse the JSON into your model class
			val gson = Gson()
			val user = gson.fromJson(json, Array<UserModel>::class.java).toMutableList()
//			val user = gson.fromJson(json, Array<UserModel>::class.java)

			// Now, you can access the data from the User object
			/*val username = user.username
			val password = user.password*/

			// Perform the login logic here
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	companion object{
//		val data = getAssets().open("user.json")
//		val data = assets.open("user.json")

//		var data= assets.open("user.json")
//		val json = data.bufferedReader().use { it.readText() }
//
//		var validation = Gson().toJson(json)
	}

/*private fun dummy() {
		val data = assets.open("user.json")

		val json = data.bufferedReader().use { it.readText() }

		var validation = Gson().toJson(json)

	}*/
	data class datauser(var id : Int? = null, var nama: String? = null, var pin: String? = null, var role : Int? = null)
}



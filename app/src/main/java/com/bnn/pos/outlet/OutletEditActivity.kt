package com.bnn.pos.outlet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.antohuang.dialogsheet.DialogSheet
import com.bnn.pos.databinding.ActivityOutletEditBinding
import com.bnn.pos.databinding.LayoutDialogConfirmationBinding
import com.bnn.pos.databinding.LayoutDialogInformationBinding
import com.bnn.pos.model.OutletModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter


class OutletEditActivity : AppCompatActivity() {

	private lateinit var model: OutletModel
	private lateinit var binding: ActivityOutletEditBinding
	private lateinit var viewModel: OutletViewModel

	private var dialogSheet: DialogSheet? = null
	private var confirmationDialog: DialogSheet? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityOutletEditBinding.inflate(layoutInflater)
		setContentView(binding.root)
		viewModel = ViewModelProvider(this@OutletEditActivity)[OutletViewModel::class.java]
		model = OutletModel()
		var can: Int = intent.getIntExtra("can", 0)

		binding.outletBack.setOnClickListener {
			finish()
		}
//		var id: Int = intent.getIntExtra("outlet_id", 0)

		if (can == 1) {
			binding.outletTitle.text = "Add Data Outlet"
			binding.outletSave.text = "Save"
			binding.outletSave.setOnClickListener {
				showInformationDialog("attention", "Data Successfully Saved")
				viewModel.inputFile()
				save()
			}
		} else {
			binding.outletTitle.text = "Edit Data Outlet"
			binding.outletSave.text = "Update"
			binding.outletSave.setOnClickListener {
				showInformationDialog("attention", "Data Successfully Updated")
				viewModel.inputFile()
				save()

			}
		}

		val intent = intent
		if (intent != null) {
			var id = intent.getIntExtra("outlet_id", 0)
			var name = intent.getStringExtra("name")
			var address = intent.getStringExtra("address")
			binding.outletName.setText(name)
			binding.outletAddress.setText(address)
//			var content = intent.getStringExtra("outletModel")
//			var model = Gson().fromJson(content, OutletModel::class.java)
//			Log.d("model", "onCreate: $model")
//			binding.outletName.setText(model.outletName)
//			binding.outletAddress.setText(model.outletAddress)
		}


		binding.outletName.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

			}

			override fun afterTextChanged(s: Editable?) {
				model.outletName = s.toString()
			}

		})
		binding.outletAddress.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

			}

			override fun afterTextChanged(s: Editable?) {
				model.outletAddress = s.toString()
			}

		})

		/*binding.outletSave.setOnClickListener {
			if (can==1)
			showInformationDialog("attention", "Data Successfully Saved", true)
			else showInformationDialog("attention", "Data Successfully Updated", true)
			finish()
			viewModel.inputFile()
			save()
		}*/

	}

	fun save() {
		FileWriter("app/src/main/assets/outlet.json").use { writer ->
			val gson = GsonBuilder().create()
			gson.toJson(OutletModel::class.java, writer)
		}
		val fos = FileOutputStream(File(filesDir, "app/src/main/assets/outlet.json"))
//		val fos = FileInputStream(File(filesDir, "app/src/main/assets/outlet.json"))
		BufferedWriter( FileWriter(getFilesDir()));
		Gson().toJson(OutletModel::class.java,FileWriter("app/src/main/assets/outlet.json"))
		/*try {
			val fos = openFileOutput("app/src/main/assets/outlet.json", MODE_PRIVATE)
			fos.write(model.outletAddress.toString().toByteArray())
			fos.close()
		} catch (e: Exception) {
			e.printStackTrace()
		}*/
		/*val path = viewModel.readFromAssets("outlet.json")
		val gson = GsonBuilder().setPrettyPrinting().create()
		val writer = BufferedWriter(FileWriter(path))
		val json = gson.toJson(writer)
		println("json: $json")
		writer.write(json)
		writer.flush()
		writer.close()*/
	}

	fun showInformationDialog(
		title: String, content: String, dialogListener: AnyDialogListener? = null
	) {
		if (dialogSheet != null) {
			return
		}
		val infoBinding = LayoutDialogInformationBinding.inflate(layoutInflater)
		dialogSheet = DialogSheet(this).setCancelable(false).setView(infoBinding.root)

		infoBinding.infoTitle.text = title
		infoBinding.infoContent.text = content
		infoBinding.infoButton.setOnClickListener {
			dialogSheet?.dismiss()
			dialogSheet = null
			dialogListener?.onPrimaryClose()
			finish()
		}

		dialogSheet?.show()

	}

	fun showConfirmationDialog(
		title: String,
		content: String,
		primaryButtonText: String?,
		secondaryButtonText: String?,
		cancelable: Boolean = true,
		dialogListener: AnyDialogListener? = null
	) {
		if (confirmationDialog != null) {
			return
		}

		val confirmDialog = LayoutDialogConfirmationBinding.inflate(layoutInflater)
		confirmationDialog = DialogSheet(this).setCancelable(cancelable).setView(confirmDialog.root)

		confirmDialog.infoTitle.text = title
		confirmDialog.infoContent.text = content
		if (primaryButtonText != null) {
			confirmDialog.infoButtonPositive.text = primaryButtonText
		}

		if (secondaryButtonText != null) {
			confirmDialog.infoButtonNegative.text = secondaryButtonText
		}

		confirmDialog.infoButtonPositive.setOnClickListener {
			confirmationDialog?.dismiss()
			dialogListener?.onPrimaryClose()
		}

		confirmDialog.infoButtonNegative.setOnClickListener {
			confirmationDialog!!.dismiss()
			dialogListener?.onSecondaryClose()
		}

		confirmationDialog?.show()
	}

	interface AnyDialogListener {
		fun onPrimaryClose()
		fun onSecondaryClose()
	}
}
package com.bnn.pos.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antohuang.dialogsheet.DialogSheet
import com.bnn.pos.R
import com.bnn.pos.databinding.ActivityEmployeeEditBinding
import com.bnn.pos.databinding.LayoutDialogConfirmationBinding
import com.bnn.pos.databinding.LayoutDialogInformationBinding

class EmployeeEditActivity : AppCompatActivity() {
	private lateinit var binding: ActivityEmployeeEditBinding

	private var dialogSheet: DialogSheet? = null
	private var confirmationDialog: DialogSheet? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityEmployeeEditBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.employeeBtnBack.setOnClickListener {
			finish()
		}
		var can: Int = intent.getIntExtra("can", 0)


		if (can == 1) {
			binding.employeeTitle.text = "Add Data employee"
			binding.employeeSave.text = "Save"
			binding.employeeSave.setOnClickListener {
				showInformationDialog("attention", "Data Successfully Saved")
			}
		} else {
			binding.employeeTitle.text = "Edit Data employee"
			binding.employeeSave.text = "Update"
			binding.employeeSave.setOnClickListener {
				showInformationDialog("attention", "Data Successfully Updated")

			}
//		binding.
		}
		if (intent != null) {
			var id = intent.getIntExtra("outlet_id", 0)
			var name = intent.getStringExtra("name")
			var address = intent.getStringExtra("address")
			binding.employeeName.setText(name)
			binding.employeeAddress.setText(address)
		}
	}

	fun showInformationDialog(
		title: String,
		content: String,
		dialogListener: AnyDialogListener? = null
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
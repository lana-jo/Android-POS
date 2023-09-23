package com.bnn.pos.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antohuang.dialogsheet.DialogSheet
import com.bnn.pos.R
import com.bnn.pos.databinding.ActivityItemEditBinding
import com.bnn.pos.databinding.LayoutDialogConfirmationBinding
import com.bnn.pos.databinding.LayoutDialogInformationBinding

class ProductEditActivity : AppCompatActivity() {

	private var dialogSheet: DialogSheet? = null
	private var confirmationDialog: DialogSheet? = null

	private lateinit var binding: ActivityItemEditBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item_edit)

		var can: Int = intent.getIntExtra("can", 0)

		if (intent != null) {
			var id = intent.getIntExtra("outlet_id", 0)
			var name = intent.getStringExtra("name")
			var address = intent.getStringExtra("address")
//			binding.name.setText(name)
//			binding.outletAddress.setText(address)
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
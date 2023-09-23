package com.bnn.pos.admindashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antohuang.dialogsheet.DialogSheet
import com.bnn.pos.LoginActivity
import com.bnn.pos.R
import com.bnn.pos.cashier.CashierActivity
import com.bnn.pos.cashier.CashierFragment
import com.bnn.pos.databinding.ActivityAdminDashboardBinding
import com.bnn.pos.databinding.LayoutDialogConfirmationBinding
import com.bnn.pos.databinding.LayoutDialogInformationBinding
import com.bnn.pos.databinding.NavHeaderBinding
import com.bnn.pos.employee.EmployeeFragment
import com.bnn.pos.outlet.OutletFragment
import com.bnn.pos.product.ProductFragment
import com.bnn.pos.setting.SettingFragment

class AdminDashboardActivity : AppCompatActivity() {
	private lateinit var binding: ActivityAdminDashboardBinding
	private var dialogSheet: DialogSheet? = null
	private var confirmationDialog: DialogSheet? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val header: NavHeaderBinding =
			NavHeaderBinding.bind(binding.nav.getHeaderView(0))
		header.button.setOnClickListener {
			/*val intent = Intent(applicationContext, CashierActivity::class.java)
			startActivity(intent)*/
			binding.drawer.closeDrawer(binding.nav)
			setCurrentFragment(CashierFragment())
			binding.nav.dispatchSetActivated(false)
			binding.nav.isSelected = false
			binding.nav.isActivated = false
//			binding.nav.checkedItem(R.id.dashboard,false)

			binding.nav.menu.findItem(R.id.dashboard).isCheckable = false
			binding.nav.menu.findItem(R.id.outlet).isCheckable = false
			binding.nav.menu.findItem(R.id.setting).isCheckable = false
			binding.nav.menu.findItem(R.id.setting).isCheckable = false
			binding.nav.menu.findItem(R.id.product).isCheckable = false
//			binding.nav.checkedItem = R.id.dashboard

		}
//		binding.nav.setCheckedItem(R.id.dashboard)
		setCurrentFragment(AdminDashboardFragment())
		binding.appbar.setNavigationOnClickListener {
			binding.drawer.openDrawer(binding.nav)
		}
		Log.d("btn", "onCreate: " + binding.nav.setCheckedItem(R.id.dashboard))
		binding.nav.setNavigationItemSelectedListener {
			it.isChecked = true
			binding.drawer.closeDrawer(binding.nav)
			when (it.itemId) {
				R.id.dashboard -> setCurrentFragment(AdminDashboardFragment())
				R.id.outlet -> setCurrentFragment(OutletFragment())
				R.id.employees -> setCurrentFragment(EmployeeFragment())
				R.id.product -> setCurrentFragment(ProductFragment())

				R.id.logout -> {
					finish()
					startActivity(Intent(this@AdminDashboardActivity, LoginActivity::class.java))
				}

				R.id.setting -> setCurrentFragment(SettingFragment())
			}
			true
		}
	}

	fun setCurrentFragment(fragment: Fragment) {
		val fragmentTransaction = supportFragmentManager.beginTransaction()
		fragmentTransaction.replace(R.id.main_frame, fragment)
		fragmentTransaction.commit()
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
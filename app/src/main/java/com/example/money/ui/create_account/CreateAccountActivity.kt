package com.example.money.ui.create_account

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.money.R
import com.example.money.model.budget_list_account.Account
import com.example.money.model.create_account.CreateAccountModel
import com.example.money.ui.base.BaseActivity
import com.example.money.ui.budget_account.IS_CREATED_KEY
import com.example.money.ui.create_account.adapter.SelectType
import com.example.money.ui.create_account.adapter.SelectTypeAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_budget_account_list.*
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_create_account.balance
import kotlinx.android.synthetic.main.activity_create_account.name
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountActivity : BaseActivity(), SelectType {
    var serviceDialog: BottomSheetDialog? = null
    private val viewModel: CreateAccountsVM by viewModel()

    override fun getActivityView(): Int = R.layout.activity_create_account

    override fun afterInflation(savedInstance: Bundle?) {
        if (isConnected()) {
            setupViews()
            //     viewModel.getBudgetAccount(id)
        }
    }

    fun setupViews() {
        toolbar_crt.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        type_txt.setOnClickListener(View.OnClickListener {
            showServiceBottomSheet(type_txt)
        })
        save.setOnClickListener(View.OnClickListener {
            createAccount()
        })
        viewModel.loading.observe(this, androidx.lifecycle.Observer {
            showLoading(it)
        })
        viewModel.createAccount.observe(this, androidx.lifecycle.Observer {
            Toast.makeText(this,"new account is created successfully !",Toast.LENGTH_LONG).show()
            returnAccountCrearedBack()
        })
    }

    fun returnAccountCrearedBack(){
        val i = Intent()
        i.putExtra(IS_CREATED_KEY, true)
        setResult(Activity.RESULT_OK, i)
        finish()
    }

    fun createAccount() {
        if (isValidated()) {
            var createAccountModel = CreateAccountModel(
                com.example.money.model.create_account.Account(
                    balance = balance.text.toString().toInt(),
                    name = name.text.toString(),
                    type = type_txt.text.toString()
                )
            )
            viewModel.createAccount(id, createAccountModel)
        }
    }

    fun isValidated(): Boolean {
        var checked = true
        if (name.text.trim().isEmpty()) {
            checked = false
            error_name.visibility = View.VISIBLE
        } else {
            error_name.visibility = View.GONE
        }

        if (type_txt.text.isNullOrEmpty()) {
            checked = false
            error_type.visibility = View.VISIBLE
        } else {
            error_type.visibility = View.GONE
        }

        if (balance.text.trim().isEmpty()) {
            checked = false
            balance_error.visibility = View.VISIBLE
        } else {
            balance_error.visibility = View.GONE
        }

        return checked
    }

    fun handleRecyclerView(it: List<Account>) {
        if (!it.isEmpty()) {
            // recycler_view.visibility = View.VISIBLE
            // var adapter: BudgetListAdapter = BudgetListAdapter(it)
            // recycler_view.adapter = adapter
        } else {
            no_data_found.visibility = View.VISIBLE

        }
    }

    companion object {
        lateinit var id: String
        fun getIntent(
            context: Context,
            id: String
        ): Intent {
            this.id = id
            return Intent(context, CreateAccountActivity::class.java)
        }
    }

    fun showServiceBottomSheet(view: View) {
        if (serviceDialog == null) {
            val inflatedViewTypes: View =
                layoutInflater.inflate(R.layout.type_bottom_sheet_layout, null)
            serviceDialog =
                this?.let { BottomSheetDialog(this, R.style.DialogSheetTheme) }!!
            serviceDialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            serviceDialog?.setCancelable(true)
            serviceDialog?.setContentView(inflatedViewTypes)
            var main_service_rcyclerview =
                inflatedViewTypes.findViewById<RecyclerView>(R.id.main_service_rcyclerview)
            var adapter: SelectTypeAdapter =
                SelectTypeAdapter(resources.getStringArray(R.array.types).toMutableList(), this)
            main_service_rcyclerview.adapter = adapter
        }
        serviceDialog?.show()

    }

    override fun handleSelectType(type: String) {
        type_txt.text = type
        serviceDialog?.dismiss()
    }


}

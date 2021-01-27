package com.example.money.ui.budget_account

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.money.R
import com.example.money.model.budget_list_account.Account
import com.example.money.ui.base.BaseActivity
import com.example.money.ui.budget_account.adapter.AccountListAdapter
import com.example.money.ui.create_account.CreateAccountActivity
import kotlinx.android.synthetic.main.activity_budget_account_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


const val CREATE_ACCOUNT_REQUEST_CODE :Int = 1
const val IS_CREATED_KEY :String = "created"

class BudgetAccountListActivity : BaseActivity() {

    private val viewModel: BudgetAccountsVM by viewModel()

    override fun getActivityView(): Int = R.layout.activity_budget_account_list

    override fun afterInflation(savedInstance: Bundle?) {
        if (isConnected()) {
            setupViews()
            viewModel.getBudgetAccount(id)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CREATE_ACCOUNT_REQUEST_CODE) {
            if (resultCode === Activity.RESULT_OK) {
                val created: Boolean = data!!.getBooleanExtra(IS_CREATED_KEY,false)
                if(created){
                    viewModel.getBudgetAccount(id)
                }
            }
        }
    }

    fun setupViews() {
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        fab.setOnClickListener(View.OnClickListener {
            startActivityForResult(CreateAccountActivity.getIntent(this,id),CREATE_ACCOUNT_REQUEST_CODE)
        })
        viewModel.loading.observe(this, androidx.lifecycle.Observer {
            showLoading(it)
        })
        viewModel.BudgetListAccount.observe(this, androidx.lifecycle.Observer {
            handleRecyclerView(it)
        })
    }

    fun handleRecyclerView(it: List<Account>) {
        if (!it.isEmpty()) {
            recycler_view.visibility = View.VISIBLE
            var adapter: AccountListAdapter = AccountListAdapter(it,this)
            recycler_view.adapter = adapter
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
            return Intent(context, BudgetAccountListActivity::class.java)
        }
    }


}

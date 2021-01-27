package com.example.money.ui.payee_transactions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.money.R
import com.example.money.model.transaction_payee.Transaction
import com.example.money.ui.base.BaseActivity
import com.example.money.ui.budget_account.adapter.AccountListAdapter
import com.example.money.ui.payee_transactions.adapter.TransactionListAdapter
import kotlinx.android.synthetic.main.activity_budget_account_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PayeeTransactionActivity : BaseActivity() {

    private val viewModel: PayeeTransactionVM by viewModel()

    override fun getActivityView(): Int = R.layout.activity_payee_transaction

    override fun afterInflation(savedInstance: Bundle?) {
        if (isConnected()) {
            setupViews()
            viewModel.getPayeeTransaction(id)
        }
    }


    fun setupViews() {
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        viewModel.loading.observe(this, androidx.lifecycle.Observer {
            showLoading(it)
        })
        viewModel.PayeeTransaction.observe(this, androidx.lifecycle.Observer {
            handleRecyclerView(it)
        })
    }

    fun handleRecyclerView(it: List<Transaction>) {
        if (!it.isEmpty()) {
            recycler_view.visibility = View.VISIBLE
            var adapter: TransactionListAdapter = TransactionListAdapter(it,this)
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
            return Intent(context, PayeeTransactionActivity::class.java)
        }
    }


}

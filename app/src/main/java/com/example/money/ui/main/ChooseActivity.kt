package com.example.money.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.money.R
import com.example.money.ui.budget_account.BudgetAccountListActivity
import com.example.money.ui.payee_transactions.PayeeTransactionActivity
import kotlinx.android.synthetic.main.activity_budget_account_list.*
import kotlinx.android.synthetic.main.activity_choose.*
import kotlinx.android.synthetic.main.activity_choose.toolbar

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        budget_accounts.setOnClickListener(View.OnClickListener {
            startActivity(BudgetAccountListActivity.getIntent(this,
                id
            ))
        })
        payee_transactions.setOnClickListener(View.OnClickListener {
            startActivity(PayeeTransactionActivity.getIntent(this,
                id
            ))
        })
    }

    companion object {
        lateinit var id: String
        fun getIntent(
            context: Context,
            id: String
        ): Intent {
            Companion.id = id
            return Intent(context, ChooseActivity::class.java)
        }
    }
}
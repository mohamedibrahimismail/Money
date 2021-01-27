package com.example.money.ui.main

import android.os.Bundle
import android.view.View
import com.example.money.ui.base.BaseActivity
import com.example.money.R
import com.example.money.model.main.Budget
import com.example.money.ui.main.Adapter.BudgetListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

    private val mainVM: MainVM by viewModel()

    override fun getActivityView(): Int = R.layout.activity_main

    override fun afterInflation(savedInstance: Bundle?) {
        if(isConnected()) {
            setupViews()
            mainVM.getBudgetList()
        }
    }

    fun setupViews() {
        mainVM.loading.observe(this, androidx.lifecycle.Observer {
            showLoading(it)
        })
        mainVM.BudgetsList.observe(this, androidx.lifecycle.Observer {
            handleRecyclerView(it.budgets)
        })
    }

    fun handleRecyclerView(it: List<Budget>) {
        if (!it.isEmpty()) {
            recycler_view.visibility = View.VISIBLE
            var adapter: BudgetListAdapter = BudgetListAdapter(it,this)
            recycler_view.adapter = adapter
        } else {
            no_data_found.visibility = View.VISIBLE

        }
    }


}

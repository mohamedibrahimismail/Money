package com.example.money.ui.payee_transactions.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.money.ui.main.ChooseActivity
import com.example.money.R
import com.example.money.model.main.Budget
import com.example.money.model.transaction_payee.Transaction
import com.example.money.ui.base.CommonVH
import kotlinx.android.synthetic.main.budget_list_item.view.*
import kotlinx.android.synthetic.main.budget_list_item.view._id
import kotlinx.android.synthetic.main.transaction_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TransactionListAdapter(
    var list: List<Transaction>,
    var context: Context
) : RecyclerView.Adapter<CommonVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_list_item, parent,false)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {
            _id.text = list.get(position).id
            amount.text = list.get(position).amount.toString()
            cleard.text = list.get(position).cleared
            account_id.text = list.get(position).account_id
            account_name.text = list.get(position).account_name
            payee_id.text = list.get(position).payee_id
            payee_name.text = list.get(position).payee_name
            category_id.text = list.get(position).category_id
            category_name.text = list.get(position).category_name
        }
    }





    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }
}

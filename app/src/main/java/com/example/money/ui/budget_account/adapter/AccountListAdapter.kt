package com.example.money.ui.budget_account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.money.R
import com.example.money.model.budget_list_account.Account
import com.example.money.ui.base.CommonVH
import com.example.money.ui.budget_account.BudgetAccountListActivity
import kotlinx.android.synthetic.main.account_list_item.view.*
import kotlinx.android.synthetic.main.budget_list_item.view.*
import kotlinx.android.synthetic.main.budget_list_item.view._id
import kotlinx.android.synthetic.main.budget_list_item.view.name
import org.jetbrains.anko.sdk27.coroutines.onClick

class AccountListAdapter(
    var list: List<Account>,
    var context: Context
) : RecyclerView.Adapter<CommonVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.account_list_item, parent,false)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {
            _id.text = list.get(position).id
            name.text = list.get(position).name
            type.text = list.get(position).type
            on_budget.text = list.get(position).on_budget.toString()
            closed.text = list.get(position).closed.toString()
            balance.text = list.get(position).balance.toString()
            cleared_balance.text = list.get(position).cleared_balance.toString()
            uncleared_balance.text = list.get(position).uncleared_balance.toString()
            transfer_payee_id.text = list.get(position).transfer_payee_id
    //        deleted.text = list.get(position).deleted.toString()

//            this.onClick {
//                context.startActivity(BudgetAccountListActivity.getIntent(context,list.get(position).id))
//            }
        }
    }





    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }
}

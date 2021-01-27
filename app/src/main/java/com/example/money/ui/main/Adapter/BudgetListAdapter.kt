package com.example.money.ui.main.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.money.ui.main.ChooseActivity
import com.example.money.R
import com.example.money.model.main.Budget
import com.example.money.ui.base.CommonVH
import kotlinx.android.synthetic.main.budget_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class BudgetListAdapter(
    var list: List<Budget>,
    var context: Context
) : RecyclerView.Adapter<CommonVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.budget_list_item, parent,false)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {
            _id.text = list.get(position).id
            name.text = list.get(position).name
            last_modified_on.text = list.get(position).last_modified_on
            first_month.text = list.get(position).first_month
            last_month.text = list.get(position).last_month
            date_format.text = list.get(position).date_format.format
            iso_code.text = list.get(position).currency_format.iso_code
            currency_symbol.text = list.get(position).currency_format.currency_symbol
            decimal_digits.text = list.get(position).currency_format.decimal_digits.toString()
            example_format.text = list.get(position).currency_format.example_format

            this.onClick {
                context.startActivity(ChooseActivity.getIntent(context,list.get(position).id))
            }
        }
    }





    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }
}

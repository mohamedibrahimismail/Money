package com.example.money.ui.create_account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.money.R
import com.example.money.ui.base.CommonVH
import kotlinx.android.synthetic.main.select_item_type.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

interface SelectType{
    fun handleSelectType(type: String)
}
class SelectTypeAdapter(
    var list: MutableList<String>,
    var selectType: SelectType,
) : RecyclerView.Adapter<CommonVH>() {

    var selected:String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.select_item_type, null)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {
            if (selected!=null && list.get(position).equals(selected)) {
                imageView3.visibility = View.VISIBLE
            } else {
                imageView3.visibility = View.GONE
            }
            Service_txt.text = list.get(position)
            this.setOnClickListener(View.OnClickListener {
                selected = list.get(position)
                notifyDataSetChanged()
                selectType.handleSelectType(selected!!)
            })
        }
    }


//    fun setSelectedList(selected_language_list: MutableList<ServiceModel>) {
//        this.selected_language_list = selected_language_list
//    }


    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }
}

package com.example.androidcourse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cat.*


class CatHolder (
    override val containerView: View,
    private val itemClick: (Cat) ->Unit
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var cat: Cat? = null

    init{
        itemView.setOnClickListener{
           cat?.also(itemClick)
        }
    }

    fun bind(cat: Cat){
        this.cat = cat
        with(cat){
            tv_description.text = description
            tv_name.text = name
        }
    }


    fun updateFields(bundle: Bundle) {
        if (bundle.containsKey("ARG_NAME")) {
            bundle.getString("ARG_NAME").also {
                Log.e("HOLDER", "ARG_NAME $it")
                tv_name.text = it
            }
        }
        if (bundle.containsKey("ARG_DESCRIPTION")) {
            bundle.getString("ARG_DESCRIPTION").also {
                Log.e("HOLDER", "ARG_DESCRIPTION $it")
                tv_description.text = it
            }
        }
    }
    companion object{

        fun create(parent: ViewGroup, itemClick: (Cat) -> Unit):
                CatHolder = CatHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat,parent,false),
            itemClick
        )
    }
}
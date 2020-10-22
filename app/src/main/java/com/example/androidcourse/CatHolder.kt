package com.example.androidcourse

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cat.*

class CatHolder (
    override val containerView: View,
    private val itemClick: (Cat) ->Unit
): RecyclerView.ViewHolder(containerView),LayoutContainer{

    private var cat: Cat? = null

    init{
        itemView.setOnClickListener{
            it.context.startActivity(Intent(it.context,AboutCatActivity::class.java).putExtra("id", cat?.id))
        }
    }

    fun bind(cat: Cat){
        this.cat = cat
        with(cat){
            tv_description.text = description
            tv_name.text = name
            iv_item_photo.setImageResource(this.photo)
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
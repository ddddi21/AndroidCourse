package com.example.androidcourse

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class CatDiffCallBack(
    ): DiffUtil.ItemCallback<Cat>()
{
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
    return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Cat, newItem: Cat): Any? {
        val diffBundle = Bundle()
        if(oldItem.name != newItem.name){
            diffBundle.putString("ARG_NAME", newItem.name)
        }
        if(oldItem.description != newItem.description){
            diffBundle.putString("ARG_DESCRIPTION",newItem.description)
        }
        return if (diffBundle.isEmpty) null
        else diffBundle
    }
}
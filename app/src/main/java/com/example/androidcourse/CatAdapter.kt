package com.example.androidcourse

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil

class CatAdapter (
    private val itemClickLambda: (Cat) -> Unit
) : ListAdapter<Cat, CatHolder>(object : DiffUtil.ItemCallback<Cat>() {

    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean = oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder =
        CatHolder.create(parent, itemClickLambda)

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<Cat>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }

}
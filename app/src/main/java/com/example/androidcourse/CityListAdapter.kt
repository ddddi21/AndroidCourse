package com.example.androidcourse

import android.view.ViewGroup

class CityListAdapter {
    private var list : List<Cat>,
    private val itemClick: (Cat) -> Unit
    ): RecyclerView.Adapter<CatHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                CatHolder = CatHolder.create(parent, itemClick)

        override fun onBindViewHolder(holder: CatHolder, position: Int) =
            holder.bind(list[position])

        override fun getItemCount(): Int = list.size

        fun updateDataSource(newList: List<Cat>){
            list = newList
            notifyDataSetChanged()
        }
}
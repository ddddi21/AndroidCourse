package com.example.androidcourse.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourse.model.City
import kotlinx.android.extensions.LayoutContainer

class WeatherHolder (
    override val containerView: View,
    private val itemClick: (Int) ->Unit
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind (city: City){


    }
    companion object{

    }
}
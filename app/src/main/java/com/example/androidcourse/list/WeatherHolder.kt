package com.example.androidcourse.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcourse.R
import com.example.androidcourse.model.City
import com.example.androidcourse.services.TempService
import com.example.androidcourse.services.TempService.Companion.findColor
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weather.*

class WeatherHolder (
    override val containerView: View
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {
    var itemClick: ((Int) -> Unit)? = null

    companion object {
        fun create(parent: ViewGroup): WeatherHolder {
            return WeatherHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false),
            )
        }
    }

    fun bind (city: City){
        var tempColor = findColor(city.temperature)
        tv_city_name.text = city.name
        tv_city_temp.let {
            it.text = city.temperature.toString()
            it.setTextColor(tempColor)
        }
        itemView.setOnClickListener{
            itemClick?.let { it1 -> it1(city.id) }
        }

    }
}
package com.example.androidcourse.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidcourse.R
import com.example.androidcourse.network.ApiFactory
import com.example.androidcourse.services.WindService
import kotlinx.android.synthetic.main.city_page_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CityPageFragment: Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private var cityId: Int? = null

    companion object{
        fun create(id: Int): CityPageFragment {
            return CityPageFragment()
                .apply {
                    arguments = Bundle().apply {putInt("id",id)}
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.city_page_fragment,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityId = arguments?.getInt("id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityId?.let {
            launch {
                val weatherResponse =
                    ApiFactory.getWeatherByCityId(
                        it
                    )
                activity?.runOnUiThread {
                    //todo
//                    iv_weather_icon = weatherResponse.weather[3].icon
                    tv_page_city_temp.text = "${weatherResponse.main.temp} "
                    tv_page_city_name.text = "Интересующий город: ${weatherResponse.sys.country}, ${weatherResponse.name}"
                    tv_page_humidity.text = "Что по влажности?: ${weatherResponse.main.humidity} %"
                    tv_page_wind_deg.text = "Ветерок: ${WindService.
                    findWind(weatherResponse.wind.deg)}, ${weatherResponse.wind.speed} м/с"
                    tv_page_description.text = "${weatherResponse.weather[0].description}"
                    tv_page_feels_like.text = "Ощущается как: ${weatherResponse.main.feelsLike}"
                    tv_page_pressure.text = "Давление: ${weatherResponse.main.pressure}"
                    tv_page_sunrise.text = "Восход солнышка: ${weatherResponse.sys.sunrise}"
                    tv_page_sunset.text = "Закат: ${weatherResponse.sys.sunset}"

                }
            }
        }
    }
}
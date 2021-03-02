package com.example.androidcourse.list

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidcourse.R
import com.example.androidcourse.model.City
import com.example.androidcourse.main.MainActivity
import com.example.androidcourse.network.ApiFactory
import com.example.androidcourse.services.WindService
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.weather_list_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeathersListFragment(val userWantToWeather: (Int)-> Unit): Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private var latitude: Double? = null
    private var longitude: Double? = null
    private var adapter: WeatherAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.weather_list_fragment, container, false)
//        val recycle = view.findViewById<RecyclerView>(R.id.recyclerView)
//
//        adapter = WeatherAdapter() {
//            Toast.makeText(context, "hello $it", Toast.LENGTH_SHORT).show()
//        }
//        adapter?.updateDataSource(CatRepository.getCats())
//        recycle.adapter = adapter
//        recycle.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        activity?.let { activity->
            val nowLocationClient =
                LocationServices.getFusedLocationProviderClient(activity)
            if (ActivityCompat.
                checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                    .checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                Log.i("message","here we go again")
                return
            }
            nowLocationClient.lastLocation.addOnSuccessListener(activity) { location: Location ->
                latitude = location.latitude
                longitude = location.longitude
                Log.d("hi", "coords: $latitude and  $longitude")
                latitude?.let{ lt->
                    longitude?.let { ln->
                        launch {
                            val listByResponse =
                                ApiFactory.getWeathersByLocation(lt, ln)
                            val cityList: ArrayList<City> = ArrayList()
                            listByResponse.forEach { weather ->
                                cityList.add(
                                    City(
                                        weather.id,
                                        weather.name,
                                        weather.main.temp,
                                        weather.wind.deg.toString(),
                                        weather.wind.speed
                                    )
                                )
                            }
                            cityList.forEach({})
                            activity.runOnUiThread {
                                recyclerView.adapter = WeatherAdapter(cityList) { int: Int ->
                                    userWantToWeather(int)
                                    Log.d("hi", int.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchingCity = searchView.query.toString()
                launch {
                    ApiFactory.getWeatherByCityName(searchingCity)?.id?.let {
                        userWantToWeather(it)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}
package com.example.androidcourse.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.androidcourse.network.ApiFactory
import com.example.androidcourse.R
import com.example.androidcourse.list.CityPageFragment
import com.example.androidcourse.list.WeathersListFragment
import com.example.androidcourse.services.PermissionChecking
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope{
    private val api = ApiFactory.weatherApi
    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private val fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var permission: PermissionChecking

    val userWantToWeather: (Int)->Unit = {
        swapFrame(
            CityPageFragment.create(it)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permission = PermissionChecking()
        permission.isGiven(this)
        swapFrame(WeathersListFragment(userWantToWeather))
    }

    fun swapFrame(frame: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_main,frame).commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(permission.handlePermissionsResult(requestCode, permissions, grantResults)){
            swapFrame(WeathersListFragment(userWantToWeather))
        } else{
            Snackbar.make(frame_main,"дайте пермишен уж", Snackbar.LENGTH_LONG).show()
        }
    }

//    private fun onHelloClick() {
//        lifecycleScope.launch {
//            api.getWeather("Kazan").run {
//                Snackbar.make(
//                    findViewById(android.R.id.content),
//                    "Шаhaр Температурасы: ${main.temp}",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
//        }
//    }
}
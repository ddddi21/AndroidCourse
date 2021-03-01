package com.example.androidcourse.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.androidcourse.network.ApiFactory
import com.example.androidcourse.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val api = ApiFactory.weatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_hello.setOnClickListener {
            onHelloClick()
        }
    }

    private fun onHelloClick() {
        lifecycleScope.launch {
            api.getWeather("Kazan").run {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Шаhaр Температурасы: ${main.temp}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
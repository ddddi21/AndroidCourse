package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mycoffee = Coffee(2,false,1)
        println("I have ${mycoffee.how_many()} coffee")
        val mylatte = Latte(1,true,1)
        println("I have ${mylatte.how_many()} latte")
        mylatte.add_more_sugar(1)
        println("Now, my latte sweeter and I have ${mylatte.sugar} spoonful of sugar")
        println("Now, I have ${mylatte.add(2,mylatte.count)} latte")
        println(mylatte.is_have_milk())
        println(mylatte.ishot())
        println(mylatte.todrink())

    }

}
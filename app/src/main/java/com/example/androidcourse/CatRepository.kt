package com.example.androidcourse

object CatRepository {

    fun getCats(): ArrayList<Cat> = arrayListOf(
        Cat(1,"Barsik","Home cat"),
        Cat(2, "Tom", "Lives outside :("),
        Cat(3,"Tommy", "In honor of Tommy Shelby")
    )
}
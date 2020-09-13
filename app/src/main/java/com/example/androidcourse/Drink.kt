package com.example.androidcourse

interface Drink {
    fun ishot()
    fun add(something_new:Int, how_be_before: Int): Int{
        var glass: Int
        glass = how_be_before + something_new
        return glass
    }
    fun todrink()
}
package com.example.androidcourse

 open class Coffee(sugar: Int, milk:Boolean, count:Int){
    var sugar = sugar
    val milk = milk
    val count = count

    init {
        println("ваш кофе подан!")
    }
     fun how_many():Int{
         return count
     }
}
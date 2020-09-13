package com.example.androidcourse

class Latte(sugar: Int, milk: Boolean, count: Int) : Coffee(sugar, milk, count),Drink {
    override fun ishot() {
        println("Latte's hot")
    }

    override fun todrink() {
        println("I'm drinking latte")
    }

    override fun add(something_new: Int,count: Int): Int {
        return super.add(something_new, this.count)
    }

    fun add_more_sugar(more_sugar: Int){
         sugar += more_sugar
    }
    fun is_have_milk(): String{
        var s = "No, my latte without milk"
        if(milk)
            s = "Yes, my latte with milk"
        return s
    }
}
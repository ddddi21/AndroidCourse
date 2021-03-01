package com.example.androidcourse.services

import com.example.androidcourse.R

abstract class TempService {

    fun findColor(temp: Double): Int{
        if(temp <= -30){
           return R.color.superCold
        } else
        if(temp <= -5 && temp > -30){
            return R.color.cold
        } else
            if(temp > -5 && temp <= 10){
                return R.color.fine
            } else
                if(temp > 10 && temp < 30){
                    return R.color.warm
                } else
                    if(temp >= 30){
                        return R.color.dyingWarm
                    }
        return 0
    }
}
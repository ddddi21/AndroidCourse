package com.example.androidcourse.services

import androidx.annotation.ColorInt
import com.example.androidcourse.R

class TempService() {

       fun findColor(temp: Int): Int {
           if (temp <= -30) {
               return R.color.superCold
           } else
               if (temp in -30..-6) {
                   return R.color.cold
               } else
                   if (temp in -5..11) {
                       return R.color.fine
                   } else
                       if (temp in 11..29) {
                           return R.color.warm
                       } else
                           if (temp >= 30) {
                               return R.color.dyingWarm
                           }
           return 0
       }
}
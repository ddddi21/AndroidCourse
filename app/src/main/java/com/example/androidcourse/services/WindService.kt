package com.example.androidcourse.services

class WindService() {
companion object {
    fun findWind(wind: Double): String {
        when (wind) {
            in 337.5..22.5 -> {
                return "..ветер северный...этапом по Твери, зла немеренно.."
            }
            in 22.5..67.5 -> {
                return "северо-восток"
            }
            in 67.5..112.5 -> {
                return "восток"
            }
            in 112.5..157.5 -> {
                return "юго-восток"
            }
            in 157.5..202.5 -> {
                return "юг"
            }
            in 202.5..247.5 -> {
                return "юго-запад"
            }
            in 247.5..292.5 -> {
                return "запад"
            }
            in 292.5..337.5 -> {
                return "северо-запад"
            }
            else -> return ""
        }
    }
}
}
package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_cat.*
import java.lang.IllegalStateException

class AboutCatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_cat)
        var thisCats = CatRepository.getCatById(intent.getIntExtra("id", 0))

        if (thisCats != null) {
            init(thisCats)
        } else {
            throw  IllegalStateException()
        }
    }
     fun init(cat:Cat) {
        tv_name.text = cat.name
        tv_age.text = "Возраст: ${cat.age.toString()}"
        tv_desc.text = "О чем: ${cat.description}"
        tv_mood.text = "Mood: ${cat.mood}"
        iv_photo.setImageResource(cat.photo)
    }
}
package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ImageView as ImageView1

class MainActivity : AppCompatActivity() {
    private  var iView: ImageView1? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        onTouchImage(iv_hey, FirstFragment())
        onTouchImage(iv_hm,SecondFragment())
        onTouchImage(iv_stoun, ThirdFragment())
        onTouchImage(iv_cool,FourFragment())
        onTouchImage(iv_kto_kuda, FiveFragment())

    }

     private fun onTouchImage(imageView: ImageView1, fragment:Fragment){
        imageView.setOnClickListener {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit()
                iView?.setColorFilter(0)
                iView = imageView
                iView?.setColorFilter(R.color.colorAccent)
        }
    }

}
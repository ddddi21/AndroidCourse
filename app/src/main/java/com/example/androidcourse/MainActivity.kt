package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_phone.setOnClickListener{
            tv_phone.visibility = View.INVISIBLE
            edit_phone.isCursorVisible = true
            edit_phone.visibility = View.VISIBLE
            tv_change_phone.visibility = View.INVISIBLE
            btn_edit_is_done.visibility = View.VISIBLE
        }
        btn_edit_is_done.setOnClickListener{
            edit_phone.visibility = View.INVISIBLE
            btn_edit_is_done.visibility = View.INVISIBLE
            tv_phone.visibility = View.VISIBLE
            tv_phone.text = edit_phone.text
            tv_change_phone.visibility = View.VISIBLE
        }

    }

}
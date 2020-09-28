package com.example.androidcourse

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        iv_done.setOnClickListener {
            var isname = false
            var isusername = false
            var isphone = false
            val name = et_registration_name.text.toString().trim()
            val intent_name = Intent(this, MainActivity::class.java)
            intent_name.putExtra("name", name)
            val username = et_registration_username.text.toString().trim()
            intent_name.putExtra("username", username)
            val phone = et_registration_phone.text.toString().trim()
            intent_name.putExtra("phone", phone)
            if (name.length < 5) {
                et_registration_name.error = "Name must have least 5 characters"
            }
            else isname = true
            if(username.length < 8) {
                et_registration_username.error = "Username must have least 8 characters"
            }
            else isusername = true
            if(phone.length < 10){
                et_registration_phone.error = "Phone must have 10 characters"
            } else isphone = true
            if(isname and isusername and isphone) {
                startActivity(intent_name)
            }
        }
        tv_phone_seven.underline()
    }
    fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}
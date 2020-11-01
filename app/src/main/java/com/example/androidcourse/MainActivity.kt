package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_fragment.*

class MainActivity : AppCompatActivity() {
    private var adapter: CatAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.text_navigation -> {
                    onTextClick()
                    true
                }
                R.id.list_navigation -> {
                    onListClick()
                    true
                }
                R.id.card_navigation -> {
                    onCardClick()
                    true
                }
                else -> false
            }
        }
        bnv_main.setOnNavigationItemReselectedListener {}
    }

    private fun onTextClick(){
        supportFragmentManager.beginTransaction().replace(R.id.frame,TextFragment()).commit()
    }

    private fun onListClick(){
        supportFragmentManager.beginTransaction().replace(R.id.frame, ListFragment()).commit()
    }

    private fun onCardClick(){
//        supportFragmentManager.beginTransaction().replace(R.id.frame,TextFragment()).commit()
    }

}
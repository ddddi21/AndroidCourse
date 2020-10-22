package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: CatAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CatAdapter(CatRepository.getCat()){
            Toast.makeText(this,"hello $it", Toast.LENGTH_SHORT).show()
        }
        rv_cat.adapter = adapter
        rv_cat.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
        }
    }
}
package com.example.androidcourse

import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_tg.setOnClickListener {
            val page = Uri.parse("https://web.telegram.org/")
            val webIntent = Intent(Intent.ACTION_VIEW, page)

            val activities: List<ResolveInfo> = packageManager.queryIntentActivities(webIntent, 0)
            val isIntentSafe: Boolean = activities.isNotEmpty()

            if (isIntentSafe) {
                startActivity(webIntent)
            }
        }
    }
}
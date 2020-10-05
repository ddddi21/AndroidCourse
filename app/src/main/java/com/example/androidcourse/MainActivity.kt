package com.example.androidcourse

import android.app.Activity
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    private val TYPE_PHOTO = 1
    private val REQUEST_CODE_PHOTO = 1
    val directory: File? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_tg.setOnClickListener{
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO))
            startActivityForResult(intent,REQUEST_CODE_PHOTO)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_PHOTO -> if(resultCode == RESULT_OK) {
                Toast.makeText(this, "Photo uri: ${intent?.data}", Toast.LENGTH_SHORT).show()
                val imgBitmap = data?.extras?.get("data") as Bitmap
                Toast.makeText(this, "bitmap ${imgBitmap.width} x ${imgBitmap.height}", Toast.LENGTH_SHORT)
                    .show()
//                iv_tg.setImageBitmap(imgBitmap)
            } else if (resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                }
            }
        }
    private fun generateFileUri(type: Int): Uri{
        var file: File? = null
        when (type){
            TYPE_PHOTO -> file= File("${directory?.path}/photo_${System.currentTimeMillis()}.jpg" )
        }
        Toast.makeText(this, "filename = $file", Toast.LENGTH_SHORT).show()
        return Uri.fromFile(file)
    }
}

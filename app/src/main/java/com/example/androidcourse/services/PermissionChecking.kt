package com.example.androidcourse.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat

const val AGREE_CODE = 1
 class PermissionChecking() {
    fun isGiven(context:Context){
        if (ActivityCompat.checkSelfPermission(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION), AGREE_CODE)
        }
    }

    fun handlePermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray):Boolean {
        when(requestCode){
            AGREE_CODE-> {
                if( grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    Log.i("Permission message", "Agree permission")
                    return true
                }
                else {
                    Log.i("Permission message", "Not agree permission")
                    return false
                }
            }
        }
        return false
    }

}
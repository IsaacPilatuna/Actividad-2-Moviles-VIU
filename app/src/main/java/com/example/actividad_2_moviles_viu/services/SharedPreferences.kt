package com.example.actividad_2_moviles_viu.services

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

class SharedPreferences @Inject constructor() {

    fun  getPreferenceString(activity: Activity, key:String):String?{
        val sharedPreferences =  activity.getSharedPreferences("AppSharedPreferences",
            Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,null)
    }

    fun storePreferenceString(activity: Activity, value: String, key:String){
        val sharedPreferences =  activity.getSharedPreferences("AppSharedPreferences",
            Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }
}
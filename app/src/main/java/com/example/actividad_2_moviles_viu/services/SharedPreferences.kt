package com.example.actividad_2_moviles_viu.services

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity

class SharedPreferences {
    companion object{
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
}
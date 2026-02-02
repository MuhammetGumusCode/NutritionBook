package com.atilsamancioglu.besinkitabi.util

import android.content.Context
import android.content.SharedPreferences

class SpecialSharedPreferences {

    companion object {

        private val TIME = "time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: SpecialSharedPreferences? = null

        private val lock = Any()




        operator fun invoke(context: Context): SpecialSharedPreferences = instance ?: synchronized(lock) {
            instance ?: CreateSpecialSharedPreferences(context).also {
                instance = it
            }
        }

        private fun CreateSpecialSharedPreferences(context: Context): SpecialSharedPreferences {
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SpecialSharedPreferences()

        }

    }


    fun saveTime(time: Long) {
                   sharedPreferences?.edit()?.putLong(TIME, time)?.apply()
    }


    fun getTime() = sharedPreferences?.getLong(TIME, 0)


}
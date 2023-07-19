package com.example.ooushfitness.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.ooushfitness.constants.OoushConstants

class SessionUtils {

    companion object {
        fun clearSession(activity: FragmentActivity?) {
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.clear()
            editor?.apply()
        }

        fun storeData(activity: FragmentActivity?, key: String, value: String?) {
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            editor?.putString(key, value)
            editor?.apply()
        }

        fun storeData(activity: FragmentActivity?, key: String, value: Int?) {
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            if (value != null) {
                editor?.putInt(key, value)
            }
            editor?.apply()
        }

        fun storeData(activity: FragmentActivity?, key: String, value: Boolean?) {
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
            if (value != null) {
                editor?.putBoolean(key, value)
            }
            editor?.apply()
        }
    }

}
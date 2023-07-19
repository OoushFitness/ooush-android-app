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

        fun userAuthenticated(activity: FragmentActivity?): Boolean {
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                return sharedPreferences.getBoolean("authenticated", false)
            }
            return false
        }

        fun getStringData(activity: FragmentActivity?, key: String): String? {
            var value: String? = null
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                value = sharedPreferences.getString(key, "")
            }
            return value
        }

        fun getBooleanData(activity: FragmentActivity?, key: String): Boolean? {
            var value: Boolean? = null
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                value = sharedPreferences.getBoolean(key, false)
            }
            return value
        }

        fun getIntData(activity: FragmentActivity?, key: String): Int? {
            var value: Int? = null
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                value = sharedPreferences.getInt(key, -1)
            }
            return value
        }

        fun getStringData(context: Context?, key: String): String? {
            var value: String? = null
            val sharedPreferences: SharedPreferences? = context?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                value = sharedPreferences.getString(key, "")
            }
            return value
        }

        fun getBooleanData(context: Context?, key: String): Boolean? {
            var value: Boolean? = null
            val sharedPreferences: SharedPreferences? = context?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                value = sharedPreferences.getBoolean(key, false)
            }
            return value
        }

        fun getIntData(context: Context?, key: String): Int? {
            var value: Int? = null
            val sharedPreferences: SharedPreferences? = context?.getSharedPreferences(OoushConstants.PREF_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                value = sharedPreferences.getInt(key, -1)
            }
            return value
        }
    }

}
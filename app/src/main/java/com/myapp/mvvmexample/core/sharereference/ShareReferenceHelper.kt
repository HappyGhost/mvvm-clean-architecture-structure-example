package com.myapp.mvvmexample.core.sharereference

import android.content.Context


public class ShareReferenceHelper(context: Context) {
    val sharedPreferences = context.getSharedPreferences(SHARE_REFERENCE_NAME_SPACE, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun getString(key: String): String = getString(key, "")

    companion object {
        const val SHARE_REFERENCE_NAME_SPACE = "privateData"
        const val SHARE_REFERENCE_USERNAME_KEY = "usernameKey"
        const val SHARE_REFERENCE_PASSWORD_KEY = "passwordKey"
    }
}
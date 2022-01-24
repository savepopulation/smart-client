package com.raqun.smartclient.lib.ext

import android.annotation.SuppressLint
import android.content.SharedPreferences

/*
 * Removes the entry with the given name
 */
fun SharedPreferences.remove(key: String) {
    this.operate {
        remove(key)
    }
}

/*
 * Applys given operation over the Shared Preferences
 */
@SuppressLint("CommitPrefEdits")
inline fun SharedPreferences.operate(operation: SharedPreferences.Editor.() -> SharedPreferences.Editor) {
    this.edit().operation().apply()
}

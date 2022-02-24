package com.raqun.smartclient.lib.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.raqun.smartclient.lib.ext.operate

// Default implementation of LocalDataSource
open class DefaultLocalDataSource(private val context: Context) : LocalDataSource {

    // Smart Client uses Android shared prefs to keep local data
    private val sharedPrefs = context.getSharedPreferences(MASTER_KEY, MODE_PRIVATE)

    // Returns the client id
    override fun getClientId(): String? {
        return sharedPrefs.getString(KEY_CLIENT_ID, null)
    }

    // Saves the generated client id
    override fun saveClientId(clientId: String) {
        sharedPrefs.operate {
            putString(KEY_CLIENT_ID, clientId)
        }
    }

    // Returns first app opening time
    override fun getFirstAppOpeningTime(): Long {
        return sharedPrefs.getLong(KEY_FIRST_OPEN, 0L)
    }

    // Saves first app opening time
    override fun saveFirstAppOpeningTime(dateTime: Long) {
        sharedPrefs.operate {
            putLong(KEY_FIRST_OPEN, dateTime)
        }
    }

    companion object {
        /*
         * Master Key for Shared Preferences
         */
        private const val MASTER_KEY = "c200cnRfY0wxM250"

        /*
         * Keys
         */
        private const val KEY_CLIENT_ID = "client_id"
        private const val KEY_FIRST_OPEN = "first_open"
    }
}

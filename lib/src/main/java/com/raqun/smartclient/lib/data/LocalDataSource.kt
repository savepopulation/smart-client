package com.raqun.smartclient.lib.data

/*
 * LocalDataSource
 * Contains the local data operation definitions.
 */
interface LocalDataSource {
    // Returns the client id
    fun getClientId(): String?

    // Saves the generated client id
    fun saveClientId(clientId: String)
}

package com.raqun.smartclient.lib

import android.annotation.SuppressLint
import android.content.Context
import com.raqun.smartclient.lib.data.DefaultLocalDataSource
import com.raqun.smartclient.lib.data.LocalDataSource
import java.util.UUID

typealias ClientIdGenerator = () -> String

@SuppressLint("StaticFieldLeak")
object SmartClient {
    private lateinit var localDataSource: LocalDataSource
    private lateinit var _clientId: String

    val clientId: String
        get() = _clientId

    fun setup(
        context: Context,
        localDataSource: LocalDataSource? = null,
        clientIdGenerator: ClientIdGenerator = { UUID.randomUUID().toString() }
    ) {
        this.localDataSource = localDataSource ?: DefaultLocalDataSource(context.applicationContext)
        _clientId = this.localDataSource.getClientId() ?: clientIdGenerator.invoke()
    }
}

package com.raqun.smartclient.lib

import android.annotation.SuppressLint
import android.content.Context
import com.raqun.smartclient.lib.data.DefaultLocalDataSource
import com.raqun.smartclient.lib.data.LocalDataSource
import com.raqun.smartclient.lib.headers.DefaultHeaderKeys
import com.raqun.smartclient.lib.headers.HeaderKeyMap
import java.util.UUID

typealias ClientIdGenerator = () -> String
typealias SessionIdGenerator = () -> String

@SuppressLint("StaticFieldLeak")
object SmartClient {
    private lateinit var localDataSource: LocalDataSource
    private lateinit var _clientId: String
    private lateinit var _sessionId: String

    val clientId: String
        get() = _clientId

    val sessionId: String
        get() = _sessionId

    fun setup(
        context: Context,
        localDataSource: LocalDataSource? = null,
        clientIdGenerator: ClientIdGenerator = { UUID.randomUUID().toString() },
        sessionIdGenerator: SessionIdGenerator = { UUID.randomUUID().toString() },
        headerKeyMap: HeaderKeyMap = DefaultHeaderKeys()
    ) {
        this.localDataSource = localDataSource ?: DefaultLocalDataSource(context.applicationContext)
        _clientId = this.localDataSource.getClientId() ?: kotlin.run {
            val clientId = clientIdGenerator.invoke()
            this.localDataSource.saveClientId(clientId)
            clientId
        }
        _sessionId = sessionIdGenerator.invoke()
    }
}

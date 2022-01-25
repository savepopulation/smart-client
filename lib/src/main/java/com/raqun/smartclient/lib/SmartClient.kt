package com.raqun.smartclient.lib

import android.annotation.SuppressLint
import android.content.Context
import com.raqun.smartclient.lib.data.DefaultLocalDataSource
import com.raqun.smartclient.lib.data.LocalDataSource
import com.raqun.smartclient.lib.device.CurrentDevice
import com.raqun.smartclient.lib.device.Device
import com.raqun.smartclient.lib.headers.DefaultHeaderKeys
import com.raqun.smartclient.lib.headers.HeaderKeyMap
import com.raqun.smartclient.lib.util.ClientIdGenerator
import com.raqun.smartclient.lib.util.SessionIdGenerator
import com.raqun.smartclient.lib.util.defaultClientIdGenerator
import com.raqun.smartclient.lib.util.defaultSessionIdGenerator
import java.util.UUID


@SuppressLint("StaticFieldLeak")
object SmartClient {
    private lateinit var device: Device
    private lateinit var localDataSource: LocalDataSource
    private lateinit var _clientId: String
    private lateinit var _sessionId: String

    val clientId: String
        get() = _clientId

    val sessionId: String
        get() = _sessionId

    fun setup(
        context: Context,
        device: Device? = null,
        localDataSource: LocalDataSource? = null,
        clientIdGenerator: ClientIdGenerator = defaultClientIdGenerator,
        sessionIdGenerator: SessionIdGenerator = defaultSessionIdGenerator,
        headerKeyMap: HeaderKeyMap = DefaultHeaderKeys()
    ) {
        this.device = device ?: CurrentDevice(context = context)
        this.localDataSource = localDataSource ?: DefaultLocalDataSource(context.applicationContext)
        _clientId = this.localDataSource.getClientId() ?: kotlin.run {
            val clientId = clientIdGenerator.invoke()
            this.localDataSource.saveClientId(clientId)
            clientId
        }
        _sessionId = sessionIdGenerator.invoke()
    }
}

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

@SuppressLint("StaticFieldLeak")
object SmartClient {

    private lateinit var device: Device
    private lateinit var localDataSource: LocalDataSource

    private lateinit var _clientId: String
    private lateinit var _sessionId: String

    private var _firstAppOpeningTime: Long = 0L

    val clientId: String
        get() = _clientId

    val sessionId: String
        get() = _sessionId

    val isFirstOpen: Boolean
        get() = _firstAppOpeningTime == 0L

    val firstAppOpeningTime: Long
        get() = _firstAppOpeningTime

    val isEmulator: Boolean
        get() = device.isDeviceEmulator

    val isRooted: Boolean
        get() = device.isDeviceRooted

    val time: Long
        get() = device.deviceTime

    val lang: String
        get() = device.deviceLang

    val type: Device.Type
        get() = device.provideDeviceType()

    val osVersion: Int
        get() = device.osVersion

    val brand: String
        get() = device.brand

    val model: String
        get() = device.model

    fun setup(
        context: Context,
        device: Device? = null,
        localDataSource: LocalDataSource? = null,
        clientIdGenerator: ClientIdGenerator = defaultClientIdGenerator,
        sessionIdGenerator: SessionIdGenerator = defaultSessionIdGenerator
    ) {
        this.device = device ?: CurrentDevice(context = context.applicationContext)
        this.localDataSource =
            localDataSource ?: DefaultLocalDataSource(context = context.applicationContext)
        _clientId = this.localDataSource.getClientId() ?: kotlin.run {
            val clientId = clientIdGenerator.invoke()
            this.localDataSource.saveClientId(clientId)
            clientId
        }
        _sessionId = sessionIdGenerator.invoke()
        initFirstAppOpeningTime()
    }

    fun generateHeaders(
        headerKeyMap: HeaderKeyMap = DefaultHeaderKeys(),
        withDefaults: Boolean = true
    ): MutableMap<String, String> {
        return mutableMapOf<String, String>().apply {
            if (withDefaults) {
                put(headerKeyMap.contentTypeKey, "application/json")
                put(headerKeyMap.acceptKey, "application/json")
            }
            put(headerKeyMap.clientIdKey, _clientId)
            put(headerKeyMap.sessionIdKey, _sessionId)
            put(headerKeyMap.deviceLocaleKey, lang)
            put(headerKeyMap.deviceBrandKey, brand.lowercase())
            put(headerKeyMap.deviceModelKey, model.lowercase())
            put(headerKeyMap.deviceOsKey, osVersion.toString())
            put(headerKeyMap.deviceOsTypeKey, "android")
            put(headerKeyMap.channelKey, "mobile")
            put(headerKeyMap.deviceTypeKey, type.name.lowercase())
            put(headerKeyMap.isEmulatorKey, isEmulator.toString())
            put(headerKeyMap.isRootedKey, isRooted.toString())
            put(headerKeyMap.timeStampKey, time.toString())
            put(headerKeyMap.isFirstOpen, isFirstOpen.toString())
        }
    }

    private fun initFirstAppOpeningTime() {
        _firstAppOpeningTime = this.localDataSource.getFirstAppOpeningTime()
        if (firstAppOpeningTime == 0L) {
            localDataSource.saveFirstAppOpeningTime(System.currentTimeMillis())
        }
    }
}

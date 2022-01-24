package com.raqun.smartclient.lib

import android.annotation.SuppressLint
import android.content.Context
import com.raqun.smartclient.lib.data.DefaultLocalDataSource
import com.raqun.smartclient.lib.data.LocalDataSource
import java.util.UUID

typealias ClientIdGenerator = () -> String

@SuppressLint("StaticFieldLeak")
object SmartClient {

    private var clientIdGenerator: ClientIdGenerator = {
        UUID.randomUUID().toString()
    }

    private lateinit var localDataSource: LocalDataSource

    fun setup(
        context: Context,
        localDataSource: LocalDataSource? = null,
        clientIdGenerator: ClientIdGenerator? = null
    ) {
        this.localDataSource = localDataSource ?: DefaultLocalDataSource(context.applicationContext)
        clientIdGenerator?.let {
            this.clientIdGenerator = it
        }
    }
}

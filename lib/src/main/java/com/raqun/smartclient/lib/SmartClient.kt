package com.raqun.smartclient.lib

import android.annotation.SuppressLint
import android.content.Context
import java.util.UUID

typealias ClientIdGenerator = () -> String

@SuppressLint("StaticFieldLeak")
object SmartClient {

    private lateinit var context: Context

    private var clientIdGenerator: ClientIdGenerator = {
        UUID.randomUUID().toString()
    }

    fun setup(
        context: Context,
        clientIdGenerator: ClientIdGenerator? = null
    ) {
        this.context = context.applicationContext
        clientIdGenerator?.let {
            this.clientIdGenerator = it
        }
    }
}

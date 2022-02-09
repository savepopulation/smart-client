package com.raqun.smartclient

import android.app.Application
import com.raqun.smartclient.lib.SmartClient

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SmartClient.setup(applicationContext)
    }
}

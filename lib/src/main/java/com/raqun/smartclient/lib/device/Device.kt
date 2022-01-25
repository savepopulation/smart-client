package com.raqun.smartclient.lib.device

import com.raqun.smartclient.lib.util.RootChecker


interface Device {
    val isDeviceEmulator: Boolean
    val isDeviceRooted: Boolean
    val deviceTime: Long
    val deviceLang: String

    val rootChecker: RootChecker

    fun provideDeviceType(): DeviceType
}

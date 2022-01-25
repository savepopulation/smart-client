package com.raqun.smartclient.lib.device

interface Device {
    val isDeviceEmulator: Boolean
    val isDeviceRooted: Boolean
    val deviceTime: Long
    val deviceLang: String

    fun provideDeviceType(): DeviceType
}

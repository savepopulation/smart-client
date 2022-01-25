package com.raqun.smartclient.lib.device

typealias RootChecker = () -> Boolean

interface Device {
    val isDeviceEmulator: Boolean
    val isDeviceRooted: Boolean
    val deviceTime: Long
    val deviceLang: String

    val rootChecker: RootChecker

    fun provideDeviceType(): DeviceType
}

package com.raqun.smartclient.lib.device

import com.raqun.smartclient.lib.util.RootChecker


interface Device {
    val isDeviceEmulator: Boolean
    val isDeviceRooted: Boolean
    val deviceTime: Long
    val deviceLang: String
    val osVersion: Int
    val brand: String
    val model: String

    val rootChecker: RootChecker

    fun provideDeviceType(): Type

    enum class Type {
        PHONE, TABLET
    }
}

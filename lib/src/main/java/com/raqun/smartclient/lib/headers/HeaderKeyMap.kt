package com.raqun.smartclient.lib.headers

interface HeaderKeyMap {
    val deviceLocale: String
    val deviceBrand: String
    val deviceModel: String
    val deviceOs: String
    val deviceOsType: String
    val channel: String
    val deviceType: String
    val isEmulator: String
    val isRooted: String
    val timeStamp: String
    val clientId: String
    val sessionId: String
}

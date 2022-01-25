package com.raqun.smartclient.lib.headers

open class DefaultHeaderKeys(
    override val deviceLocale: String = "x-device-locale",
    override val deviceBrand: String = "x-device-brand",
    override val deviceModel: String = "x-device-model",
    override val deviceOs: String = "x-device-os",
    override val deviceOsType: String = "x-device-os-type",
    override val channel: String = "x-channel",
    override val deviceType: String = "x-device-type",
    override val isEmulator: String = "x-app-is-emulator",
    override val isRooted: String = "x-is-rooted",
    override val timeStamp: String = "x-timestamp",
    override val clientId: String = "x-client-id",
    override val sessionId: String = "x-session-id"
) : HeaderKeyMap
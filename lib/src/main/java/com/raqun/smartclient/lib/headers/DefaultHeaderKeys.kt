package com.raqun.smartclient.lib.headers

open class DefaultHeaderKeys(
    override val contentTypeKey: String = "Content-Type",
    override val acceptKey: String = "Accept",
    override val deviceLocaleKey: String = "x-device-locale",
    override val deviceBrandKey: String = "x-device-brand",
    override val deviceModelKey: String = "x-device-model",
    override val deviceOsKey: String = "x-device-os",
    override val deviceOsTypeKey: String = "x-device-os-type",
    override val channelKey: String = "x-channel",
    override val deviceTypeKey: String = "x-device-type",
    override val isEmulatorKey: String = "x-app-is-emulator",
    override val isRootedKey: String = "x-is-rooted",
    override val timeStampKey: String = "x-timestamp",
    override val clientIdKey: String = "x-client-id",
    override val sessionIdKey: String = "x-session-id",
    override val isFirstOpen: String = "x-is-first-open"
) : HeaderKeyMap
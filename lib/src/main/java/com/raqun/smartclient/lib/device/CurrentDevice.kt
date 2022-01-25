package com.raqun.smartclient.lib.device

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.raqun.smartclient.lib.util.Emulator
import java.io.File
import java.util.*


internal val defaultRootChecker: RootChecker = {
    val isEmulator: Boolean = Emulator.isDeviceEmulator()
    val buildTags = Build.TAGS
    if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
        true
    } else {
        var file = File("/system/app/Superuser.apk")
        if (file.exists()) {
            true
        } else {
            file = File("/system/xbin/su")
            !isEmulator && file.exists()
        }
    }
}

open class CurrentDevice(
    private val context: Context,
    override val rootChecker: RootChecker = defaultRootChecker
) : Device {

    override val isDeviceEmulator: Boolean
        get() = Emulator.isDeviceEmulator()

    override val isDeviceRooted: Boolean
        get() = rootChecker.invoke()

    override val deviceTime: Long
        get() = System.currentTimeMillis()

    override val deviceLang: String
        get() = Locale.getDefault().language

    override fun provideDeviceType(): DeviceType {
        val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return if (manager.phoneType == TelephonyManager.PHONE_TYPE_NONE) {
            DeviceType.TABLET
        } else {
            DeviceType.PHONE
        }
    }
}

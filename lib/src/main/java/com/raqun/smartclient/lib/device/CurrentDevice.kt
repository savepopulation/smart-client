package com.raqun.smartclient.lib.device

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.raqun.smartclient.lib.util.RootChecker
import com.raqun.smartclient.lib.util.checkIfDeviceEmulator
import com.raqun.smartclient.lib.util.defaultRootChecker
import java.util.Locale

class CurrentDevice(
    private val context: Context,
    override val rootChecker: RootChecker = defaultRootChecker
) : Device {

    override val isDeviceEmulator: Boolean = checkIfDeviceEmulator()

    override val isDeviceRooted: Boolean = rootChecker.invoke()

    override val deviceTime: Long
        get() = System.currentTimeMillis()

    override val deviceLang: String
        get() = Locale.getDefault().language

    override val osVersion: Int
        get() = Build.VERSION.SDK_INT

    override val brand: String
        get() = Build.MANUFACTURER

    override val model: String
        get() = Build.MODEL

    override fun provideDeviceType(): Device.Type {
        val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return if (manager.phoneType == TelephonyManager.PHONE_TYPE_NONE) {
            Device.Type.TABLET
        } else {
            Device.Type.PHONE
        }
    }
}

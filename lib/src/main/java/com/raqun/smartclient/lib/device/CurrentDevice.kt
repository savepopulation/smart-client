package com.raqun.smartclient.lib.device

import android.content.Context
import android.telephony.TelephonyManager
import com.raqun.smartclient.lib.util.RootChecker
import com.raqun.smartclient.lib.util.checkIfDeviceEmulator
import com.raqun.smartclient.lib.util.defaultRootChecker
import java.util.Locale

open class CurrentDevice(
    private val context: Context,
    override val rootChecker: RootChecker = defaultRootChecker
) : Device {

    override val isDeviceEmulator: Boolean
        get() = checkIfDeviceEmulator()

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

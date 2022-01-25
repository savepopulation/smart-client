package com.raqun.smartclient.lib.util

import android.os.Build

class Emulator private constructor() {
    companion object {
        fun isDeviceEmulator() = Build.FINGERPRINT.contains("generic")
    }
}

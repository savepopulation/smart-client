package com.raqun.smartclient.lib.util

import android.os.Build

fun checkIfDeviceEmulator() = Build.FINGERPRINT.contains("generic")


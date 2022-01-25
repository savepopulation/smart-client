package com.raqun.smartclient.lib.util

import android.os.Build
import java.io.File

typealias RootChecker = () -> Boolean

val defaultRootChecker: RootChecker = {
    val isEmulator: Boolean = checkIfDeviceEmulator()
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
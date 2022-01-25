package com.raqun.smartclient.lib.util

import java.util.*

typealias ClientIdGenerator = () -> String
typealias SessionIdGenerator = () -> String

val defaultClientIdGenerator: ClientIdGenerator = {
    UUID.randomUUID().toString()
}

val defaultSessionIdGenerator: SessionIdGenerator = {
    UUID.randomUUID().toString()
}

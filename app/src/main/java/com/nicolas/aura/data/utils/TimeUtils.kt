package com.nicolas.aura.data.utils

import android.os.Build
import java.time.Instant

fun getCurrentTimestamp(): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.now().toEpochMilli()
    } else {
        System.currentTimeMillis()
    }
}
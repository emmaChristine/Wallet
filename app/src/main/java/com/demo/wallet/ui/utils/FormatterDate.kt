package com.demo.wallet.ui.utils

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

fun formatDate(date: Date): String? {

    val f = DateTimeFormatter
        .ofPattern("EEEE MMM dd hh:mm:ss yyyy")
        .withZone(ZoneOffset.UTC)
        .format(Instant.now())


    return f.format(date)
}
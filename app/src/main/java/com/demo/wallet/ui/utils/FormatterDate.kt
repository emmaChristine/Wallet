package com.demo.wallet.ui.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String? {

    val sdf = SimpleDateFormat("EEEE MMM dd hh:mm:ss yyyy", Locale.US)

   return  sdf.format(date)
}
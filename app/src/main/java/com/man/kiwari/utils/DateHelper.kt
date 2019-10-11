package com.man.kiwari.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getDate(time: Long): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(time)
    }

    fun getTime(time: Long): String {
        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(time)
    }
}
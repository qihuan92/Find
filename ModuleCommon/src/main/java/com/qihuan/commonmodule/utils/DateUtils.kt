package com.qihuan.commonmodule.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * DateUtils
 * Created by Qi on 2017/7/12.
 */

private const val F0 = "yyyyMMdd"

fun getNowDate(): String {
    val simpleDateFormat = SimpleDateFormat(F0, Locale.getDefault())
    return getNowString(simpleDateFormat)
}

private fun getNowString(sdf: SimpleDateFormat): String {
    return sdf.format(Date())
}

fun getNowDate(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    return getNowString(simpleDateFormat)
}

fun timeSub(date: String, dayAddNum: Int = 1, format: String = F0): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    try {
        val newDate = Date(simpleDateFormat.parse(date).time - dayAddNum * 24 * 60 * 60 * 1000)
        return simpleDateFormat.format(newDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return ""
}

fun timeAdd(date: String, dayAddNum: Int = 1, format: String = F0): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
    try {
        val newDate = Date(simpleDateFormat.parse(date).time + dayAddNum * 24 * 60 * 60 * 1000)
        return simpleDateFormat.format(newDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return ""
}

fun parseDate(date: String?): String {
    if (date == null) {
        return ""
    }

    val format = SimpleDateFormat(F0, Locale.getDefault())
    if (date == getNowString(format)) {
        return "今天"
    }

    var formatDate: Date? = null
    try {
        formatDate = format.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    if (formatDate == null) {
        return ""
    }

    val simpleDateFormat = SimpleDateFormat("- yyyy/MM/dd EEEE -", Locale.getDefault())
    return simpleDateFormat.format(formatDate)
}
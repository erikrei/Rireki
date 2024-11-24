package com.example.rireki.data.util

import java.time.LocalDate

fun getReadableStringOfLocalDate(date: LocalDate): String {
    val monthName = getMonthName(date)

    return "${date.dayOfMonth}. $monthName ${date.year}"
}